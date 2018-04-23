package zyudemo

import grails.gorm.transactions.Transactional
import groovyx.net.http.HttpBuilder

@Transactional
class PayActionService {

    def http = HttpBuilder.configure {
        request.uri = 'http://jspay.wiipay.cn/'
    }

    def isActive() {
        [resultType: "success"]
    }

    private Client userValidation(String userKey, String userLicense) {
        Client client = Client.findByKey(userKey)
        if (!client || client.license != userLicense) {
            throw new Exception("client")
        }
        return client;
    }

    private PayType payTypeValidation(String type) {
        PayType payType = PayType.findByKey(type);
        if (!PayType || !payType.working) {
            throw new Exception("payType")
        }
        return payType
    }

    final static String api_key = "im8jvs8unejblx277a5cyendm6usx6iz"
    final static String channel_id = "default"
    final static String format = "json"
    final static String version = "2.0"
    final static String app_id = "a0666fa64f77fdcd63c03e08f28535a1"

    /**
     * 调用支付接口
     * @param api_url
     * @param app_id
     * @param body
     * @param total_fee
     * @param out_trade_no
     * @param callback_url
     * @return
     */
    private String callApiToPay(String api_url, String body, String total_fee, String out_trade_no, String callback_url) {
        String sign_prep = String.format("app_id=%s&body=%s&callback_url=%s&channel_id=%s&format=%s&out_trade_no=%s&total_fee=%s&version=%s%s",
                app_id, body, callback_url, channel_id, format, out_trade_no, total_fee, version, api_key)
        String sign = sign_prep.encodeAsMD5().toString().toUpperCase()

        String result = http.post {
            request.uri.path = api_url
            request.contentType = 'application/x-www-form-urlencoded'
            request.body = [app_id: app_id, body: body, callback_url: callback_url,
                            channel_id: channel_id, format: format, out_trade_no: out_trade_no,
                            total_fee: total_fee, version: version, sign: sign]
        }

        if (result.endsWith("state=success}")) {
            return result.split(",").find {it.startsWith(" pay_url")}.split("=", 2)[1]
        } else {
            return  result.split(",").find {it.startsWith(" ret_msg")}.split("=", 2)[1]
        }
    }

    /**
     * 生成订单号
     * @param type
     * @return
     */
    def generateTradeNo(String type) {
        String.format("%s_%s", type, String.valueOf(System.currentTimeMillis()))
    }

    /**
     * 同步数据时验证签名
     * @param orderNo
     * @param synType
     * @param status
     * @param price
     * @param time
     * @param cpparam
     * @param sign
     * @return
     */
    def syncSignCheck(String orderNo, String synType, String status, String price, String time, String cpparam, String sign) {
        String signCheck = String.format("cpparam=%s&orderNo=%s&price=%s&status=%s&synType=%s&time=%s%s",
                cpparam, orderNo, price, status, synType, time, api_key).encodeAsMD5().toString().toUpperCase()
        signCheck == sign
    }

    /**
     * 同步数据
     * @param orderNo
     * @param synType
     * @param status
     * @param price
     * @param time
     * @param cpparam
     * @return
     */
    def doSync(String orderNo, String synType, String status, String price, String time, String cpparam) {
        PayRecord payRecord = PayRecord.findByTradeNo(cpparam)
        payRecord.backDate = time
        payRecord.orderNo = orderNo
        payRecord.backFee = price
        payRecord.backStatus = status
        payRecord.backType = synType
        payRecord.syncStatus = 1
        payRecord.save()

        def syncHttp =  HttpBuilder.configure {
            request.uri = payRecord.client.syncUrl == "test" ? "http://localhost:8080/zyu/syncTest" : payRecord.client.syncUrl
        }

        String result = syncHttp.post {
            request.contentType = 'application/x-www-form-urlencoded'
            request.body = [orderNo: cpparam, tradeNo: payRecord.clientOrderNo, syncType: synType, status: status, price:price, time: time, sign: ""]
        }

        if (result.contains("success")) {
            payRecord.syncStatus = 3
            payRecord.save()
        } else {
            payRecord.syncStatus = 4
            payRecord.save()
        }

    }

    def payAction(String body, String total_fee, String userKey, String userLicense, String callbackUrl, def service, String clientOrderNo) throws Exception {
        Client client = userValidation(userKey, userLicense)
        PayType payType = payTypeValidation(service.getType())
        String out_trade_no = generateTradeNo(payType.getKey())
        saveRecord(body, total_fee, payType, out_trade_no, client, clientOrderNo)
        String api_url = service.getApiUrl();
        callApiToPay(api_url, body, total_fee, out_trade_no, callbackUrl)   // 调用支付接口
    }

    private static void saveRecord(String body, String fee, PayType payType, String out_trade_no, Client client, String clientOrderNo) {
        new PayRecord(body:body, fee: fee, payType: payType, tradeNo: out_trade_no, client: client, clientOrderNo: clientOrderNo, syncStatus: 0).save()
    }
}

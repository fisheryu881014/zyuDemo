package zyudemo

import grails.converters.JSON

class PayActionController {

    def payActionService
    def qqPayActionService
    def wxPayActionService
    def aliPayActionService
    def jdPayActionService
    def visaPayActionService
    def quickPayActionService

    def index() {
        def result = payActionService.isActive()
        render result as JSON
    }

    def pay(String body, String fee, String type, String key, String license, String callbackUrl) {
        type = type ?: "default"
        def typeActionService = null
        switch (type.toUpperCase()) {
            case "QQ":
                typeActionService = qqPayActionService
                break
            case "WX":
                typeActionService = wxPayActionService
                break
            case "ALI":
                typeActionService = aliPayActionService
                break
            case "JD":
                typeActionService = jdPayActionService
                break
//            case "VISA":
//                typeActionService = visaPayActionService
//                break
            case "QUICK":
                typeActionService = quickPayActionService
                break
            default:
                break
        }
        if (!typeActionService) {
            redirect url: "/failed/to/pay?result=payType"
        }
        try {
            redirect url: payActionService.payAction(body, fee, key, license, callbackUrl, typeActionService)
        } catch (Exception e) {
            redirect url: "/failed/to/pay?result=${e.getMessage()}"
        }
    }

    def sync(String orderNo, String synType, String status, String price, String time, String cpparam, String sign) {
        if (payActionService.syncSignCheck(orderNo, synType, status, price, time, cpparam, sign)) {
            payActionService.doSync(orderNo, synType, status, price, time, cpparam)
            render "success"
        } else {
            render "failed"
        }
    }

    def payFailed(String result) {
        String message = "身份验证失败"
        if (result == "payType") {
            message = "支付类型错误或暂不可用"
//            render [messege: "支付类型错误或暂不可用"] as JSON
//        } else {
//            render [message: "身份验证失败"] as JSON
        }
        [message: message] as JSON
    }
}

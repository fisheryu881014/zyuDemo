package zyudemo

class BootStrap {

    def init = { servletContext ->
        if (!Client.findAll()) {
            Client client = new Client(name: "Test Client", description: "Just for test", key: "1a2b3c", action: true, syncUrl: "test")
            client.license = String.format("%s_%s", client.name, client.key).encodeAsMD5().toString()
            client.save()
        }

        if (!PayType.findAll()) {
            PayType qqPayType = new PayType(name: "QQ", key: "QQ", working: true, company: "微派", payHost: "http://jspay.wiipay.cn/", payUri: "/1/jspay/qq/qq_api.do").save()
            PayType wxPayType = new PayType(name: "微信", key: "WX", working: true, company: "微派", payHost: "http://jspay.wiipay.cn/", payUri: "/1/jspay/wxh5/wx_api.do").save()
            PayType aliPayType = new PayType(name: "支付宝", key: "ALI", working: true, company: "微派", payHost: "http://jspay.wiipay.cn/", payUri: "/1/jspay/ali/ali_api.do").save()
            PayType jdPayType = new PayType(name: "京东", key: "JD", working: true, company: "微派", payHost: "http://jspay.wiipay.cn/", payUri: "/1/jspay/jd/jd_api.do").save()
            PayType visaPayType = new PayType(name: "网银", key: "VISA", working: true, company: "微派", payHost: "http://jspay.wiipay.cn/", payUri: "/1/jspay/un/un_api.do").save()
            PayType quickPayType = new PayType(name: "快捷", key: "QUICK", working: true, company: "微派", payHost: "http://jspay.wiipay.cn/", payUri: "/1/jspay/un/un_api.do").save()
        }
    }
    def destroy = {
    }
}

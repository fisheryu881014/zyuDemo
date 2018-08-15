package zyudemo

import grails.converters.JSON

class ClientController {

    private UserType weChatType = UserType.findByKey("wechat")
    private UserType mobileType = UserType.findByKey("mobile")

    def index() {
        render "success"
    }

    def loginWithWeChat(String openId) {
        Client client = Client.findByOpenId(openId)
        if (client == null) {
            client = new Client(name: "wechat name", openId: openId, type: weChatType)
            client.save()
        }
        render client as JSON
    }

    def loginWithMobile(String mobile) {
        Client client = Client.findByMobile(mobile)
        if (client == null) {
            client = new Client(name: "wechat name", mobile: mobile, type: mobileType).save()
        }
        render client as JSON
    }
}

package zyudemo

import grails.converters.JSON
import org.springframework.beans.factory.annotation.Autowired

class ClientController {

    @Autowired
    private ClientService clientService

    private UserType weChatType = UserType.findByKey("wechat")
    private UserType mobileType = UserType.findByKey("mobile")

    def index() {
        render "success"
    }

    // todo 登录相关,微信登录完善
    def loginWithWeChat(String openId) {
        Client client = Client.findByOpenId(openId)
        if (client == null) {
            client = new Client(name: "wechat name", openId: openId, type: weChatType)
            client.save()
        }
        render client as JSON
    }

    def weChatLogin(String code) {
        render clientService.getUserInfoFromWeChat(code)
    }
    // todo 登录，手机登录完善
    def loginWithMobile(String mobile) {
        Client client = Client.findByMobile(mobile)
        if (client == null) {
            client = new Client(name: "wechat name", mobile: mobile, type: mobileType).save()
        }
        render client as JSON
    }

    // todo, 用户分享
}

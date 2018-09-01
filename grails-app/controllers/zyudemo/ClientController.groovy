package zyudemo

import grails.converters.JSON
import org.springframework.beans.factory.annotation.Autowired

class ClientController {

    @Autowired
    private ClientService clientService

    def index() {
        render "success"
    }

    def weChatLogin(String code) {
        render clientService.loginWithWeChat(code) as JSON
    }

    // todo, 用户分享
}

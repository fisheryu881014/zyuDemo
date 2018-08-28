package zyudemo

import grails.gorm.transactions.Transactional
import groovyx.net.http.HttpBuilder

@Transactional
class ClientService {

    def http = HttpBuilder.configure {
        request.uri = "https://api.weixin.qq.com/"
    }

    def http1 = HttpBuilder.configure {
        request.uri = "api.weixin.qq.com/"
    }
    def getUserInfoFromWeChat(String code) {

        String result = http.get {
            request.uri.path = URLEncoder.encode("/sns/jscode2session?appid=wx6f46232fa9e41bb3&secret=9ccc8bf3146d4347c1a929c550789298&js_code=${code}&grant_type=authorization_code")

        }
        return result
    }
}

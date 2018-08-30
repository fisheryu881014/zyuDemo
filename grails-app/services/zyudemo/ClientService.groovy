package zyudemo

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import groovyx.net.http.HttpBuilder
import org.grails.web.json.JSONObject

@Transactional
class ClientService {

    def http = HttpBuilder.configure {
        request.uri = "https://api.weixin.qq.com/"
    }
    def getUserInfoFromWeChat(String code) {
        String demo =  '{"session_key":"KOYQLZ7GUxxXyq7JFIMnVg==","openid":"oKGXT5OWsrtqmBZfmqz72SyuuhgE"}'
        String result = http.get {
            request.uri.path = "/sns/jscode2session"
            request.uri.query = [appid: 'wx6f46232fa9e41bb3', secret: '9ccc8bf3146d4347c1a929c550789298', js_code: code, grant_type:'authorization_code']
        }
        def jsonResult = JSON.parse(demo)
        return jsonResult
    }
}

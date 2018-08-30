package zyudemo

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import groovyx.net.http.HttpBuilder
import org.grails.web.json.JSONObject

@Transactional
class ClientService {

    private UserType weChatType = UserType.findByKey("wechat")

    def http = HttpBuilder.configure {
        request.uri = "https://api.weixin.qq.com/"
    }

    def loginWithWeChat(String code) {
        String result = weChatGet("/sns/jscode2session",
                [appid: 'wx6f46232fa9e41bb3', secret: '9ccc8bf3146d4347c1a929c550789298',
                 js_code: code, grant_type:'authorization_code'])
        result = '{"session_key":"KOYQLZ7GUxxXyq7JFIMnVg==","openid":"oKGXT5OWsrtqmBZfmqz72SyuuhgE"}'
        getClientByResp(result)
    }

    def getClientByResp(String result) {
        JSONObject resultObj = JSON.parse(result)
        if (resultObj.has("errcode")) {
            [success: false, code: resultObj.getString("errcode")]
        } else {
            String openid = resultObj.getString("openid")
            Client client = Client.findByOpenId(openid) ?:new Client(openId: openid, type: weChatType).save()
            [success: true, code: 0, openId: client.getOpenId(), userId: client.getId()]
        }
    }

    def weChatGet(String url, def params) {
        http.get {
            request.uri.path = url
            request.uri.query = params
        }
    }
}

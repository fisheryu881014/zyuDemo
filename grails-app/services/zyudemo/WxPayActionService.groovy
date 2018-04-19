package zyudemo

import grails.gorm.transactions.Transactional

@Transactional
class WxPayActionService {

    def getApiUrl() {
        "/1/jspay/wxh5/wx_api.do"
    }
    def getType() {
        "WX"
    }
}

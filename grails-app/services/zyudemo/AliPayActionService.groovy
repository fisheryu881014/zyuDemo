package zyudemo

import grails.gorm.transactions.Transactional

@Transactional
class AliPayActionService {

    def getApiUrl() {
        "/1/jspay/ali/ali_api.do"
    }
    def getType() {
        "ALI"
    }
}

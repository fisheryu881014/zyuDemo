package zyudemo

import grails.gorm.transactions.Transactional

@Transactional
class QuickApyActionService {

    def getApiUrl() {
        "/1/jspay/un/un_api.do"
    }
    def getType() {
        "QUICK"
    }
}

package zyudemo

import grails.gorm.transactions.Transactional

@Transactional
class VisaPayActionService {

    def getApiUrl() {
        "/1/jspay/un/un_api.do" // todo
    }
    def getType() {
        "VISA"
    }
}

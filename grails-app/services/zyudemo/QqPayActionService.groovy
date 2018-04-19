package zyudemo

import grails.gorm.transactions.Transactional

@Transactional
class QqPayActionService {
     def getType() {
        return "QQ"
     }

    def getApiUrl() {
        return "/1/jspay/qq/qq_api.do"
    }
}

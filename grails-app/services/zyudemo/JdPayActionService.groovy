package zyudemo

import grails.gorm.transactions.Transactional

@Transactional
class JdPayActionService {

    def getApiUrl() {
        "/1/jspay/jd/jd_api.do"
    }
    def getType() {
        "JD"
    }
}

package zyudemo

class PayType {

    static constraints = {
    }

//    Integer id
    // 支付方式
    String name
    // 代码标识
    String key
    // 当前状态
    Boolean working

    Date dateCreated
    Date lastUpdated

    static hasMany = [payRecords: PayRecord]
}

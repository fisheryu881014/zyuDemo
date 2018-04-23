package zyudemo

class PayType {

    static constraints = {
    }

//    Integer id
    // 支付方式，如微信
    String name
    // 代码标识，如wx
    String key
    // 当前状态，正常/关闭
    Boolean working

    Date dateCreated
    Date lastUpdated

    // 来源名，如微派
    String company

    // 域名前缀，同一公司前缀可能相同
    String payHost

    // 支付链接后缀
    String payUri

    static hasMany = [payRecords: PayRecord]
}

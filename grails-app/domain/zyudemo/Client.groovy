package zyudemo

class Client {

    static constraints = {
    }

//    Integer id
    // 用户名
    String name
    // 描述
    String description
    // 用户授权码
    String key
    //
    String license
    // 当前状态
    Boolean action

    Date dateCreated
    Date lastUpdated

    static hasMany = [payRecords: PayRecord]
}

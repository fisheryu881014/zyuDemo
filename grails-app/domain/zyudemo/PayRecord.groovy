package zyudemo

class PayRecord {

    static constraints = {
        backDate nullable: true
        orderNo nullable: true
        backFee nullable: true
        backType nullable: true
        backStatus nullable: true
    }

//    Integer id
    String body
    String fee
    String tradeNo

    Date dateCreated
    Date lastUpdated

    String backDate // 同步时间
    String orderNo // 同步订单号
    String backFee // 同步时返回的支付费用
    String backType
    String backStatus

    static belongsTo = [payType: PayType, client: Client]
}

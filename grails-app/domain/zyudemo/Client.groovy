package zyudemo

class Client {

    static constraints = {
        openId nullable: true;
        uuid nullable: true;
        mobile nullable: true;
    }

    String name
    String openId
    String uuid
    String mobile
    UserType type;

    static hasMany = [wants: Book, reading:Book, read: Book]




}

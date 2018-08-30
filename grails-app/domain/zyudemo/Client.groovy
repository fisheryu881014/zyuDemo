package zyudemo

class Client {

    static constraints = {
        name nullable: true;
        openId nullable: true;
        uuid nullable: true;
        mobile nullable: true;

        wants nullable: true;
        reading nullable: true;
        read nullable: true;
    }

    String name
    String openId
    String uuid
    String mobile
    UserType type;

    static hasMany = [wants: Book, reading:Book, read: Book]




}

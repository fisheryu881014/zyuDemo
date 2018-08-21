package zyudemo

import org.grails.web.json.JSONObject

class Book {

    static constraints = {
        date nullable: true
        press nullable: true
        authors nullable: true
        translators nullable: true
        tags nullable: true

        want nullable: true;
        reading nullable: true;
        read nullable: true;
    }
    /*

     */
    String name
    String summary
    String date
    String press
    String authors
    String translators
    String tags

    Date dateCreated
    Date lastUpdated

//    static hasOne = [press: Press]

    static hasMany = [want: Client, read: Client, reading: Client]

    static belongsTo = [Client]

//    def fromJson(JSONObject json) {
//        this.name = json.getString("name");
//        this.name = json.getString("name");
//        this.name = json.getString("name");
//        this.name = json.getString("name");
//        this.name = json.getString("name");
//        return book
//    }
}

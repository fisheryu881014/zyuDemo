package zyudemo

class Book {

    static constraints = {
        date nullable: true
    }
    /*

     */
    String name
    String summary
    Date date

    Date dateCreated
    Date lastUpdated

    static hasOne = [press: Press]

    static hasMany = [authors: Author, translater: Author]



}

package zyudemo

class Book {

    static constraints = {
        date nullable: true

        translater nullable: true

        want nullable: true;
        reading nullable: true;
        read nullable: true;
    }
    /*

     */
    String name
    String summary
    Date date

    Date dateCreated
    Date lastUpdated

    static hasOne = [press: Press]

    static hasMany = [authors: Author, translater: Author, want: Client, read: Client, reading: Client]

    static belongsTo = [Author, Client]



}

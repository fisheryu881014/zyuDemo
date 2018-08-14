package zyudemo

class Author {

    static constraints = {
        country nullable: true
        summary nullable: true
    }

    String name
    String country
    String summary

    static hasMany = [works:Book, translated: Book]
}

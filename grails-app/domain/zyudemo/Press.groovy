package zyudemo

class Press {

    static constraints = {
    }

    String name

    static hasMany = [publication: Book]
}

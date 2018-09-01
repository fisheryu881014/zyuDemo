package zyudemo

import grails.converters.JSON

class BookController {

    def bookService;

    def index() { render "index" }

    def sync() {
        render bookService.syncBooks(request.JSON) as JSON
    }

    def addToWant(Integer bookId, Integer clientId) {
        render bookService.addBookToClient(bookId, clientId, "wants") as JSON
    }

    def addToRead(Integer bookId, Integer clientId) {
        render bookService.addBookToClient(bookId, clientId, "read") as JSON
    }

    def addToReading(Integer bookId, Integer clientId) {
        render bookService.addBookToClient(bookId, clientId, "reading") as JSON
    }


}


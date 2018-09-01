package zyudemo

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

@Transactional
class BookService {

    def syncBooks(JSONArray jsonBooks) {
        Integer newBooks = 0
        for (JSONObject bookObj : jsonBooks) {
            Book book = Book.findByName(bookObj.getString("name"))
            if (book == null) {
                book = new Book(name: bookObj.getString("name"), summary: bookObj.get("summary", ""),
                        date: bookObj.get("date"), press: bookObj.get("press"), authors: bookObj.get("authors"),
                        translators: bookObj.get("translators"), tags: bookObj.get("tags"));
                book.save()
                newBooks += 1
            }
        }
        [count: newBooks]
    }

    def addBookToClient(Integer bookId, Integer clientId, String kind) {
        Client client = Client.findById(clientId)
        Book book = Book.findById(bookId)
        Boolean addSuccess
        switch (kind) {
            case "wants":
                client.addToWants(book)
                client.removeFromRead(book)
                client.removeFromReading(book)
                addSuccess = true
                break
            case "read":
                client.removeFromWants(book)
                client.addToRead(book)
                client.removeFromReading(book);
                addSuccess = true
                break
            case "reading":
                client.removeFromWants(book)
                client.removeFromRead(book)
                client.addToReading(book)
                addSuccess = true
                break
            default:
                addSuccess = false
        }
        [success: addSuccess]

    }
}



















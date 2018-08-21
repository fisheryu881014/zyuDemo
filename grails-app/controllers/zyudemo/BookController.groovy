package zyudemo

class BookController {

    def index() { render "index" }

    def bookWant(Integer bookId, Integer clientId) {
        Book book = Book.findById(bookId)
        Client client = Client.findById(clientId);

        if (book != null && client != null) {
            client.addToWants(book)
            client.save();
            render "add success"
            return
        }
        render "failed to add"
    }

    def bookReed(Integer bookId, Integer clientId) {
        Book book = Book.findById(bookId)
        Client client = Client.findById(clientId);

        if (book != null && client != null) {
            client.addToRead(book)
            client.save();
            render "add success"
            return
        }
        render "failed to add"
    }

    def bookReading(Integer bookId, Integer clientId) {
        Book book = Book.findById(bookId)
        Client client = Client.findById(clientId);

        if (book != null && client != null) {
            client.addToReading(book)
            client.save();
            render "add success"
            return
        }
        render "failed to add"
    }

    def bookSync() {
        for (Book book :request.JSON) {
            if (Book.findByName(book.getName())!= null) {
                continue
            }
            book.save();
        }
    }


}

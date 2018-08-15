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


}

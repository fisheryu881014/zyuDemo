package zyudemo

class BootStrap {

    def init = { servletContext ->
        UserType innerType
        Author innerAuthor
        Author innerTranslator
        Press innerPress
        Book book
        if (!UserType.findAll()) {
            innerType = new UserType(key: "inner", name: "内部类型", summary: "特殊用户类型，不对外开放").save()
        }

        if (!Author.findAll()) {
            innerAuthor = new Author(name: "Inner Author").save();
            innerTranslator = new Author(name: "Inner Translator").save()
        }

        if(!Press.findAll()) {
            innerPress = new Press(name: "Inner Press").save()
        }

        if (!Book.findAll()) {
            book = new Book(name: "Inner Book", summary: "简介", date: new Date())
                    .addToAuthors(innerAuthor)
                    .addToTranslater(innerTranslator)
            book.setPress(innerPress);
            book.save()
        }

        if(!Client.findAll()) {
            Client client = new Client(name:"Inner client", type: innerType).save()
            client.addToWants(book).save()
            client.addToRead(book).save()
//            client.addToReading(book).save();
        }
    }
    def destroy = {
    }
}

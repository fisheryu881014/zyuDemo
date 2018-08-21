package zyudemo

class BootStrap {

    /**
     * 1 用户微信登录
     * 2 用户信息分享
     * 3 保存书籍信息
     * 4 加入已读，想读，在读
     *
     */

    def init = { servletContext ->
        UserType innerType
//        Author innerAuthor
//        Author innerTranslator
//        Press innerPress
        Book book
        if (!UserType.findAll()) {
            innerType = new UserType(key: "inner", name: "内部类型", summary: "特殊用户类型，不对外开放").save()
            UserType mobileType = new UserType(key: "mobile", name: "手机号注册用户", summary: "手机号注册用户").save()
            UserType weChatType = new UserType(key: "wechat", name: "微信注册用户", summary: "微信注册用户").save()
        }

//        if (!Author.findAll()) {
//            innerAuthor = new Author(name: "Inner Author").save();
//            innerTranslator = new Author(name: "Inner Translator").save()
//        }

//        if(!Press.findAll()) {
//            innerPress = new Press(name: "Inner Press").save()
//        }

        if (!Book.findAll()) {
            book = new Book(name: "Inner Book", summary: "简介", date: new Date(), authors: "author1", translators: "translator1", press: "press1",tags: "tag1,tag2")
            book.save()
        }

        if(!Client.findAll()) {
            Client client = new Client(name:"Inner client", type: innerType).save()
            client.addToWants(book)
            client.addToRead(book)
            client.addToReading(book)
            client.save()
        }

        Client clientSaved = Client.findById(1)
    }
    def destroy = {
    }
}

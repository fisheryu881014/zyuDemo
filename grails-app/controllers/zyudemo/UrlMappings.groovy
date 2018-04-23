package zyudemo

class UrlMappings {

    static mappings = {
        "/zyu/index"    (controller: "payAction", action: "index", method: "GET")
        "/zyu/pay"      (controller: "payAction", action: "pay", method: "POST")
        "/zyu/sync"     (controller: "payAction", action: "sync", method: "POST")
        "/zyu/syncTest"     (controller: "payAction", action: "syncTest", method: "POST")

        "/failed/to/pay" (controller: "payAction", action: "payFailed", method: "GET")

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}

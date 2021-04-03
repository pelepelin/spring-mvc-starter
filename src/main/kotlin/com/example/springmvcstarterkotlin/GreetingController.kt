package com.example.springmvcstarterkotlin

import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class GreetingController {
    @RequestMapping(path = ["/"], produces = [MediaType.TEXT_HTML_VALUE])
    @ResponseBody
    fun greeting(
        @RequestParam(value = "name", required = false, defaultValue = "World") name: String
    ) = "<h1>Hello, $name</h1>"
}

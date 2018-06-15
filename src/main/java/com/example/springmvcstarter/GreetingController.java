package com.example.springmvcstarter;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

    @RequestMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String greeting(
        @RequestParam(value="name", required=false, defaultValue="World") String name
    ) {
        return String.format(
            "<h1>Hello, %s</h1>",
            name
        );
    }
}

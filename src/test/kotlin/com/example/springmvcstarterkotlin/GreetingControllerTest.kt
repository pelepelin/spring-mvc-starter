package com.example.springmvcstarterkotlin

import org.hamcrest.CustomTypeSafeMatcher
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.regex.Pattern

@WebMvcTest(GreetingController::class)
class GreetingControllerTest(@Autowired val mvc: MockMvc) {

    @Test
    @Throws(Exception::class)
    fun testGreetingReturnsGreetingWhenNoParameter() {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.header().string("content-type", TextHtmlMatcher))
            .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Hello, World")))
    }

    @Test
    @Throws(Exception::class)
    fun testGreetingReturnsGreetingWithNameParameter() {
        mvc.perform(MockMvcRequestBuilders.get("/?name=Bill").accept(MediaType.TEXT_HTML))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.header().string("content-type", TextHtmlMatcher))
            .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Hello, Bill")))
    }

    private object TextHtmlMatcher : CustomTypeSafeMatcher<String>("matches text/html;charset=utf-8") {
        override fun matchesSafely(item: String): Boolean {
            return Pattern.compile("text/html\\s*;\\s*charset=(?i)utf-8")
                .matcher(item).matches()
        }
    }
}

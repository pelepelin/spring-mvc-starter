package com.example.springmvcstarterkotlin;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.regex.Pattern;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

// either works
@WebMvcTest(GreetingController.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class GreetingControllerTest {
  private static final Matcher<String> TEXT_HTML_MATCHER =
      new CustomTypeSafeMatcher<String>("matches text/html;charset=utf-8") {
        @Override
        protected boolean matchesSafely(String item) {
          return Pattern.compile("text/html\\s*;\\s*charset=(?i)utf-8")
              .matcher(item).matches();
        }
      };

  @Autowired
  private MockMvc mvc;

  @Test
  public void testGreetingReturnsGreetingWhenNoParameter() throws Exception {
    mvc.perform(get("/").accept(MediaType.TEXT_HTML))
        .andExpect(status().isOk())
        .andExpect(header().string("content-type", TEXT_HTML_MATCHER))
        .andExpect(content().string(containsString("Hello, World")));
  }

  @Test
  public void testGreetingReturnsGreetingWithNameParameter() throws Exception {
    mvc.perform(get("/?name=Bill").accept(MediaType.TEXT_HTML))
        .andExpect(status().isOk())
        .andExpect(header().string("content-type", TEXT_HTML_MATCHER))
        .andExpect(content().string(containsString("Hello, Bill")));
  }
}

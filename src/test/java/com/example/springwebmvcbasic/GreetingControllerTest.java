package com.example.springwebmvcbasic;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.regex.Pattern;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
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
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGreetingReturnsGreetingWhenNoParameter() throws Exception {
        mockMvc.perform(get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(header().string("content-type", TEXT_HTML_MATCHER))
                .andExpect(content().string(containsString("Hello, World")));
    }

    @Test
    public void testGreetingReturnsGreetingWithNameParameter() throws Exception {
        mockMvc.perform(get("/?name=Bill").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(header().string("content-type", TEXT_HTML_MATCHER))
                .andExpect(content().string(containsString("Hello, Bill")));
    }
}

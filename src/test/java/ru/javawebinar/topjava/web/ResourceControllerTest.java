package ru.javawebinar.topjava.web;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ResourceControllerTest extends AbstractControllerTest{

    @Test
    public void testCss() throws Exception {
        mockMvc.perform(get("/resources/css/style.css")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}

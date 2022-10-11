package com.offside.game.gascounter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
class MeasureMonitoringControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldReturnEmptyListRusult() throws Exception {
        mockMvc
                .perform(get("/api/v1/history/?userId=4"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnOkMessage() throws Exception {
        mockMvc
                .perform(post("/api/v1/publish")
                        .content("{  \"measureType\": \"GAS\",  \"userId\": \"3\",  \"value\": 10.4 }")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"message\":\"Measure was saved\"}")));
    }

    @Test
    public void shouldReturnErrorMessage() throws Exception {
        mockMvc
                .perform(post("/api/v1/publish")
                        .content("{  \"measureType\": \"GAS\", \"value\": 10.4 }")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("userId must be not null")));;
    }


}
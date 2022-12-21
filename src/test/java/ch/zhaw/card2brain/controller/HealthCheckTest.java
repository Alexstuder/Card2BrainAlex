package ch.zhaw.card2brain.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = HealthCheck.class)
class HealthCheckTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getHealthCheck() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/healthCheck").accept(
                MediaType.TEXT_HTML);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString().contains("<!DOCTYPE html><html><head></head><titel></titel><body><h1>Card2Brain is running!</h1> <p>App.Version :"));


    }
}

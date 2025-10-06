package org.example.apifetch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifetch.service.ApiProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers = {ApiController.class})
@ExtendWith(MockitoExtension.class)
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ApiProductService productService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void apiController_getProducts_returnSuccess() throws Exception {

    }

}

package org.example.apifetch.controller;

import org.example.apifetch.service.ApiCategoryService;
import org.example.apifetch.service.ApiProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = {ApiController.class})
@ExtendWith(MockitoExtension.class)
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ApiProductService productService;

    @MockitoBean
    private ApiCategoryService categoryService;

    @Test
    void apiController_getProducts_returnSuccess() throws Exception {
        mockMvc.perform(post("/loadapi")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}

package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasLength;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCustomerSignUpIsCreated() throws Exception {
        // given
        // when
        mockMvc.perform(post("/beer-shop-app/user/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"example@email.com\",\n" +
                        "  \"password\" : \"password\",\n" +
                        "  \"fio\" : \"Петров Петр Петрович\",\n" +
                        "  \"birthDate\" : \"19.01.1995\" \n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("token", hasLength(144)));
    }

    @Test
    public void testStudentSignInIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(post("/beer-shop-app/user/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"example@email.com\",\n" +
                        "  \"password\" : \"password\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(144)));
    }
}

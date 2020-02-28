package com.example.demo.controller;

import static org.hamcrest.Matchers.hasLength;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class AuthControllerTest extends AbstractControllerTest {

    @Test
    public void testStudentSignUpIsCreated() throws Exception {
        // given
        willReturn(Optional.empty(), Optional.of(createAuthInfo())).given(authInfoRepository)
                .findByLogin("example@email.com");
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
                .andExpect(jsonPath("token", hasLength(147)));
    }

    @Test
    public void testStudentSignUpWhenUserAlreadyExisted() throws Exception {
        // given
        signInAsCustomer();
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
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testStudentSignInIsOk() throws Exception {
        // given
        signInAsCustomer();
        // when
        mockMvc.perform(post("/beer-shop-app/user/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"example@email.com\",\n" +
                        "  \"password\" : \"password\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(147)));
    }

    @Test
    public void testStudentSignInWithWrongPassword() throws Exception {
        // given
        signInAsCustomer();
        // when
        mockMvc.perform(post("/beer-shop-app/user/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"vasya@email.com\",\n" +
                        "  \"password\" : \"wrongPassword\"\n" +
                        "}"))
                // then
                .andExpect(status().isForbidden());
    }

    @Test
    public void testStudentSignInWithWrongEmail() throws Exception {
        // given
        signInAsCustomer();
        // when
        mockMvc.perform(post("/beer-shop-app/user/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"noSuchVasyavasya@email.com\",\n" +
                        "  \"password\" : \"wrongPassword\"\n" +
                        "}"))
                // then
                .andExpect(status().isForbidden());
    }
}

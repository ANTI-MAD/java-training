package com.example.demo.controller;

import static org.hamcrest.Matchers.hasLength;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import com.example.demo.security.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class AuthControllerTest extends AbstractControllerTest {

    @Test
    public void testCustomerSignUpIsCreated() throws Exception {
        // given
        willReturn(Optional.empty(), Optional.of(createAuthInfo(UserRole.CUSTOMER))).given(authInfoRepository)
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
    public void testCustomerSignUpWhenUserAlreadyExisted() throws Exception {
        // given
        signIn(UserRole.CUSTOMER);
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
    public void testCustomerSignInIsOk() throws Exception {
        // given
        signIn(UserRole.CUSTOMER);
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
    public void testCustomerSignInWithWrongPassword() throws Exception {
        // given
        signIn(UserRole.CUSTOMER);
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
    public void testCustomerSignInWithWrongEmail() throws Exception {
        // given
        signIn(UserRole.CUSTOMER);
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

package com.example.demo.controller;

import static org.hamcrest.Matchers.hasLength;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class AuthControllerTest extends AbstractControllerTest{

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
    public void testCustomerSignInIsOk() throws Exception {
        // given
        final User user = new User("example@email.com", passwordEncoder.encode("password"),
                List.of(new SimpleGrantedAuthority("CUSTOMER")));
        willReturn(user).given(loadUserDetailService).loadUserByUsername("example@email.com");
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

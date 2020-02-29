package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

public class BeerControllerTest extends AbstractControllerTest {

    @Test
    public void testGetBeersIsOk() throws Exception {
        final String token = signInAsCustomer();

        mockMvc.perform(get("/beer-shop-app/beers/list").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "      \"id\" : 1,\n" +
                        "      \"type\" : \"Трипель\",\n" +
                        "      \"name\" : \"Maredsous 10° Triple\",\n" +
                        "      \"alcohol\" : \"10.0%\",\n" +
                        "      \"volume\" : \"0.5\",\n" +
                        "      \"price\" : 3,\n" +
                        "      \"description\" : \"Бельгийский трипель со слегка сладковатым карамельно-хлебным вкусом, с фруктовыми нотками и пряной хмелевой горчинкой.\",\n" +
                        "      \"brewery\" : \"Abbaye de Maredsous\",\n" +
                        "      \"stockBalance\" : 20\n" +
                        "    }\n" +
                        "]"));
    }

    @Test
    public void testAddNewBeerIsOk() throws Exception {
        final String token = signInAsCustomer();

        mockMvc.perform(post("/beer-shop-app/beers").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "      \"type\" : \"Трипель\",\n" +
                        "      \"name\" : \"Maredsous 10° Triple\",\n" +
                        "      \"alcohol\" : \"10.0%\",\n" +
                        "      \"volume\" : \"0.5\",\n" +
                        "      \"price\" : 3,\n" +
                        "      \"description\" : \"Бельгийский трипель со слегка сладковатым карамельно-хлебным вкусом, с фруктовыми нотками и пряной хмелевой горчинкой.\",\n" +
                        "      \"brewery\" : \"Abbaye de Maredsous\",\n" +
                        "      \"stockBalance\" : 20\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "  \"response\" : \"Beer Maredsous 10° Triple successfully added\"\n" +
                        "}"));
    }

    @Test
    public void testDeleteBeerIsOk() throws Exception {
        final String token = signInAsCustomer();

        mockMvc.perform(delete("/beer-shop-app/beers/1").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"response\" : \"Maredsous 10° Triple\"\n" +
                        "}"));
    }

    @Test
    public void testUpdatePrice() throws Exception {
        final String token = signInAsCustomer();

        mockMvc.perform(put("/beer-shop-app/beers/1").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"price\": 3.15\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"response\" : \"Price changed to 3.15\"\n" +
                        "}"));
    }
}

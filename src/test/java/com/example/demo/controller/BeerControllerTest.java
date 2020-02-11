package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBeerListIsOk() throws Exception {
        mockMvc.perform(get("/beerShop-app/beer")
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
        mockMvc.perform(post("/beerShop-app/beer/add")
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
                        "  \"id\" : 3\n" +
                        "}"));
    }

    @Test
    public void testDeleteBeerIsOk() throws Exception {
        mockMvc.perform(delete("/beerShop-app/beer/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }

    @Test
    public void testUpdateCostBeer() throws Exception {
        mockMvc.perform(patch("/beerShop-app/beer/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"price\": 3.15\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }
}

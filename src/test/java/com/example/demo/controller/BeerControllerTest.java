package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.BDDMockito.given;

import com.example.demo.dto.Message;
import com.example.demo.entity.BeerEntity;
import com.example.demo.security.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BeerControllerTest extends AbstractControllerTest {

    @Test
    public void testGetBeersIsOk() throws Exception {
        final String token = signIn(UserRole.CUSTOMER);
        final List<BeerEntity> testBeer = getBeers();
        given(beerRepository.findAll()).willReturn(testBeer);

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
        verify(beerRepository, times(1)).findAll();
    }

    @Test
    public void testAddNewBeerIsOk() throws Exception {
        final String token = signIn(UserRole.ADMIN);
        final BeerEntity testBeer = getBeer();
        given(beerRepository.findById(1L)).willReturn(Optional.of(testBeer));

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
        verify(beerRepository, times(2)).save(any(BeerEntity.class));
    }

    @Test
    public void testDeleteBeerIsOk() throws Exception {
        final String token = signIn(UserRole.ADMIN);
        final BeerEntity testBeer = getBeer();
        given(beerRepository.findById(1L)).willReturn(Optional.of(testBeer));

        mockMvc.perform(delete("/beer-shop-app/beers/1").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"response\" : \"Maredsous 10° Triple\"\n" +
                        "}"));
    }

    @Test
    public void testUpdatePrice() throws Exception {
        final String token = signIn(UserRole.ADMIN);
        final BeerEntity testBeer = getBeer();
        given(beerRepository.findById(1L)).willReturn(Optional.of(testBeer));

        mockMvc.perform(post("/beer-shop-app/beers/1").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"price\": 3.15\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"response\" : \"Price changed to 3.15\"\n" +
                        "}"));
    }

    private List<BeerEntity> getBeers() {
        final BeerEntity beer = new BeerEntity();
        beer.setId(1L);
        beer.setType("Трипель");
        beer.setName("Maredsous 10° Triple");
        beer.setAlcohol("10.0%");
        beer.setVolume("0.5");
        beer.setPrice(3D);
        beer.setDescription("Бельгийский трипель со слегка сладковатым карамельно-хлебным вкусом, с фруктовыми нотками и пряной хмелевой горчинкой.");
        beer.setBrewery("Abbaye de Maredsous");
        beer.setStockBalance(20);
        beerRepository.save(beer);

        return Arrays.asList(beer);
    }

    private BeerEntity getBeer() {
        final BeerEntity beer = new BeerEntity();
        beer.setId(1L);
        beer.setType("Трипель");
        beer.setName("Maredsous 10° Triple");
        beer.setAlcohol("10.0%");
        beer.setVolume("0.5");
        beer.setPrice(3D);
        beer.setDescription("Бельгийский трипель со слегка сладковатым карамельно-хлебным вкусом, с фруктовыми нотками и пряной хмелевой горчинкой.");
        beer.setBrewery("Abbaye de Maredsous");
        beer.setStockBalance(20);
        beerRepository.save(beer);

        return beer;
    }
}

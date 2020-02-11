package com.example.demo.service;

import com.example.demo.dto.Beer;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Log
@Service
public class BeerService {

    public String getBeerList() {
        return "[\n" +
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
                "]";
    }

    public String addNewBeer(final Beer beer){
        return "{\"id\":3}";
    }

    public String deleteBeer(final Long beerId){
        return "{\"id\":1}";
    }

    public String updateCostBeer(final Long beerId){
        return "{\"id\":1}";
    }
}

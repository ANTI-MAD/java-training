package com.example.demo.service;

import com.example.demo.dto.Beer;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BeerService {

    public List<Beer> getBeers() {
        List<Beer> list = new LinkedList<>();

        list.add(Beer.builder()
                .id(1L)
                .type("Трипель")
                .name("Maredsous 10° Triple")
                .alcohol("10.0%")
                .volume("0.5")
                .price(3D)
                .description("Бельгийский трипель со слегка сладковатым карамельно-хлебным вкусом, " +
                        "с фруктовыми нотками и пряной хмелевой горчинкой.")
                .brewery("Abbaye de Maredsous")
                .stockBalance(20)
                .build());
        return list;
    }

    public String addNewBeer(final Beer beer){
        return "{\"id\":3}";
    }

    public String deleteBeer(final Long beerId){
        return "{\"id\":1}";
    }

    public String updatePrice(final Long beerId){
        return "{\"id\":1}";
    }
}

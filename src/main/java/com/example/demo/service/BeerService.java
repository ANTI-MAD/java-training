package com.example.demo.service;

import com.example.demo.dto.Beer;
import com.example.demo.dto.Message;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BeerService {
    List<Beer> list = new LinkedList<>();

    public List<Beer> getBeers() {
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

    public Message addNewBeer(final Beer beer){
        //List<Beer> list = new LinkedList<>();
        beer.builder()
                .id(1L)
                .type("Трипель")
                .name("Maredsous 10° Triple New")
                .alcohol("10.0%")
                .volume("0.5")
                .price(3D)
                .description("Бельгийский трипель со слегка сладковатым карамельно-хлебным вкусом, " +
                        "с фруктовыми нотками и пряной хмелевой горчинкой.")
                .brewery("Abbaye de Maredsous")
                .stockBalance(20)
                .build();

        return Message.builder().response("Beer " + beer.getName() + " successfully added").build();
    }

    public Message deleteBeer(final Long beerId){
        int index = 0;
        String name = getBeers().get(index).getName();
        getBeers().remove(index);
        return Message.builder().response(name).build();
    }

    public Message updatePrice(final Long beerId){
        int index = 0;
        getBeers().get(index).setPrice(3.15);
        return Message.builder().response("Price changed to " + getBeers().get(index).getPrice()).build();
    }
}

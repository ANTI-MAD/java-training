package com.example.demo.service;

import com.example.demo.dto.Beer;
import com.example.demo.dto.Message;
import com.example.demo.exception.ShopNoSuchElementException;
import com.example.demo.mapper.BeerMapper;
import com.example.demo.repository.BeerRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    /*@PostConstruct
    public void init() {
        beerRepository.save(beerMapper.sourceToDestination(Beer.builder()
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
                .build()));
    }*/

    public List<Beer> getBeers() {
        //init();
        return beerRepository.findAll().stream().map(beerMapper::destinationToSource).collect(Collectors.toList());
    }

    public Message addNewBeer(final Beer beer){
        //List<Beer> list = new LinkedList<>();
        beerRepository.save(beerMapper.sourceToDestination(Beer.builder()
                .id(1L)
                .type("Трипель")
                .name("Maredsous 10° Triple New")
                .alcohol("10.0%")
                .volume("0.5")
                .price(3D)
                .description("Бельгийский трипель со слегка сладковатым карамельно-хлебным вкусом, " +
                        "с фруктовыми нотками и пряной хмелевой горчинкой.")
                .brewery("Abbaye de Maredsous")
                .stockBalance(40)
                .build()));

        return Message.builder().response("Beer " + beerRepository.findById(1L).get().getName() + " successfully added").build();
    }

    public Message deleteBeer(final Long beerId) throws ShopNoSuchElementException {
        if (beerRepository.findById(beerId).isEmpty()) {
            throw new ShopNoSuchElementException("Beer with " + beerId + " not founded");
        }
        String name = beerRepository.findById(beerId).get().getName();
        beerRepository.deleteById(beerId);
        return Message.builder().response(name).build();
    }

    public Message updatePrice(final Long beerId) throws ShopNoSuchElementException {
        if (beerRepository.findById(beerId).isEmpty()) {
            throw new ShopNoSuchElementException("Beer with " + beerId + " not founded");
        }
        beerRepository.findById(beerId).get().setPrice(3.15);
        return Message.builder().response("Price changed to " + beerRepository.findById(beerId).get().getPrice()).build();
    }
}

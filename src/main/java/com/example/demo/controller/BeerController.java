package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.Beer;
import com.example.demo.dto.Message;
import com.example.demo.service.BeerService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Log
@Data
@RestController
@RequestMapping("/beer-shop-app/beers")
public class BeerController {
    private final BeerService beerService;

    @GetMapping(value = "/list")
    public List<Beer> getBeers(){
        return beerService.getBeers();
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Message addNewBeer(@RequestBody final Beer beer){
        return beerService.addNewBeer(beer);
    }

    @DeleteMapping(value = "/{beerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Message deleteBeer(@PathVariable final Long beerId){
        return beerService.deleteBeer(beerId);
    }

    @PutMapping(value = "/{beerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Message updatePrice(@PathVariable final Long beerId){
        return beerService.updatePrice(beerId);
    }
}

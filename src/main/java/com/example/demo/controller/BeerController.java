package com.example.demo.controller;

import com.example.demo.dto.Beer;
import com.example.demo.service.BeerService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Log
@Data
@RestController
@RequestMapping("/beerShop-app/beer")
public class BeerController {
    private final BeerService beerService;

    @GetMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getBeerList(){
        return beerService.getBeerList();
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewBeer(@RequestBody final Beer beer){
        return beerService.addNewBeer(beer);
    }

    @DeleteMapping(value = "/{beerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteBeer(@PathVariable final Long beerId){
        return beerService.deleteBeer(beerId);
    }

    @PatchMapping(value = "/{beerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String updateCostBeer(@PathVariable final Long beerId){
        return beerService.updateCostBeer(beerId);
    }
}

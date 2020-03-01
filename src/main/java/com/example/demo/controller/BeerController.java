package com.example.demo.controller;

import com.example.demo.dto.Beer;
import com.example.demo.dto.Message;
import com.example.demo.exception.ShopNoSuchElementException;
import com.example.demo.service.BeerService;
import java.util.List;

import io.swagger.annotations.*;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Log
@Data
@RestController
@RequestMapping("/beer-shop-app/beers")
@Api(value = "Beers system")
public class BeerController {
    private final BeerService beerService;

    @GetMapping(value = "/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View all beers", notes = "Use this method to view all beers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get beers"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public List<Beer> getBeers(){
        return beerService.getBeers();
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add new beer", notes = "Use this method to add new beer")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully add new beer"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public Message addNewBeer(@ApiParam(value = "Beer data", required = true)
                                  @RequestBody final Beer beer){
        return beerService.addNewBeer(beer);
    }

    @DeleteMapping(value = "/{beerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete beer", notes = "Use this method to delete beer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully delete beer"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public Message deleteBeer(@ApiParam(value = "Beer id", required = true)
                                  @PathVariable final Long beerId) throws ShopNoSuchElementException {
        return beerService.deleteBeer(beerId);
    }

    @PostMapping(value = "/{beerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update price", notes = "Use this method to update price of beer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully update price"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public Message updatePrice(@ApiParam(value = "Beer id", required = true)
                                   @PathVariable final Long beerId) throws ShopNoSuchElementException{
        return beerService.updatePrice(beerId);
    }
}

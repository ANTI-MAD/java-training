package com.example.demo.mapper;

import com.example.demo.dto.Beer;
import com.example.demo.entity.BeerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeerMapper {
    BeerEntity sourceToDestination(Beer source);
    Beer destinationToSource(BeerEntity destination);
}

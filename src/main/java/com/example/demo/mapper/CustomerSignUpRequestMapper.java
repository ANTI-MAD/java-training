package com.example.demo.mapper;

import com.example.demo.dto.CustomerSignUpRequest;
import com.example.demo.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerSignUpRequestMapper {

    UserEntity sourceToDestination(CustomerSignUpRequest source);

    CustomerSignUpRequest destinationToSource(UserEntity destination);
}

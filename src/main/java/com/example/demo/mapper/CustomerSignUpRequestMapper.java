package com.example.demo.mapper;

import com.example.demo.dto.UserSignUpRequest;
import com.example.demo.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerSignUpRequestMapper {

    UserEntity sourceToDestination(UserSignUpRequest source);

    UserSignUpRequest destinationToSource(UserEntity destination);
}

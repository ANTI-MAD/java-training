package com.example.demo.mapper;

import com.example.demo.dto.Customer;
import com.example.demo.entity.UserEntity;
import org.mapstruct.Mapper;

/**
 * @author Wladimir Litvinov
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    UserEntity sourceToDestination(Customer source);

    Customer destinationToSource(UserEntity destination);
}

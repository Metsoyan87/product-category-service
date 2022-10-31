package com.example.productcategoryservice.maper;

import com.example.productcategoryservice.dto.UserCreateDto;
import com.example.productcategoryservice.dto.UserDto;
import com.example.productcategoryservice.model.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(target = "role", defaultValue = "USER")
    User map(UserCreateDto userCreateDto);

    UserDto map(User user);
}

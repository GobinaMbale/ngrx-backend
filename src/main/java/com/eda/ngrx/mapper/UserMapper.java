package com.eda.ngrx.mapper;

import com.eda.ngrx.dto.UserDTO;
import com.eda.ngrx.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO entityToDTO(User entity);
    User dtoToEntity(UserDTO dto);
}

package com.recipes.demo.transformer.service;

import com.recipes.demo.dto.UserDTO;
import com.recipes.demo.entity.UserEntity;
import com.recipes.demo.request.UserDetailsRequestModel;
import com.recipes.demo.response.UserRest;

public interface UserTransformerService {
	
	UserRest userDtoToRest(UserDTO user);

	UserDTO userReqToDto(UserDetailsRequestModel users);

	UserEntity userDtoToEntity(UserDTO users);

	UserDTO userEntityToDTO(UserEntity users);
	
}

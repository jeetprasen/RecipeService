package com.recipes.demo.transformer.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipes.demo.dto.UserDTO;
import com.recipes.demo.entity.UserEntity;
import com.recipes.demo.request.UserDetailsRequestModel;
import com.recipes.demo.response.UserRest;
import com.recipes.demo.transformer.service.UserTransformerService;

@Service
public class UserTransformerServiceImpl implements UserTransformerService {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserRest userDtoToRest(UserDTO user) {

		return modelMapper.map(user, UserRest.class);
	}

	@Override
	public UserDTO userEntityToDTO(UserEntity users) {

		return modelMapper.map(users, UserDTO.class);
	}

	@Override
	public UserEntity userDtoToEntity(UserDTO users) {

		return modelMapper.map(users, UserEntity.class);
	}

	@Override
	public UserDTO userReqToDto(UserDetailsRequestModel users) {

		return modelMapper.map(users, UserDTO.class);
	}

}

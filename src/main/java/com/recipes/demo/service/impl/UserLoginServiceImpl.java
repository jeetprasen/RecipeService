package com.recipes.demo.service.impl;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.recipes.demo.dto.UserDTO;
import com.recipes.demo.dto.Utils;
import com.recipes.demo.entity.UserEntity;
import com.recipes.demo.repository.UserRepository;
import com.recipes.demo.request.UserDetailsRequestModel;
import com.recipes.demo.request.UserLoginRequest;
import com.recipes.demo.response.UserLoginResponse;
import com.recipes.demo.response.UserRest;
import com.recipes.demo.service.UserLoginService;
import com.recipes.demo.transformer.service.UserTransformerService;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private Utils utils;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserTransformerService userTransformerService;

	final Function<UserDetailsRequestModel, UserDTO> reqToDTO = reqToDTO();
	final Function<UserDTO, UserEntity> dtoToEntity = dtoToEntity();
	final Function<UserEntity, UserDTO> entityToDTO = entityToDTO();
	final Function<UserDTO, UserRest> dtoToRest = dtoToRest();
	final Function<UserEntity, UserEntity> saveUser = saveUser();

	@Override
	public UserLoginResponse login(UserLoginRequest req) {

		UserLoginResponse resp = new UserLoginResponse();

		Optional<UserEntity> user = userRepository.findUserByEmailAndEncryptedPassword(req.getEmail(),
				req.getPassword());

		if (user.isPresent()) {
			resp.setEmail(user.get().getEmail());
			resp.setUserId(user.get().getUserId());
		} else {
			resp.setEmail("");
		}

		return resp;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserEntity> userEntity = userRepository.findUserByEmail(email);
		if (userEntity.isPresent()) {
			return new User(userEntity.get().getEmail(), userEntity.get().getEncryptedPassword(), new ArrayList<>());
		}
		throw new RuntimeException(email);
	}

	@Override
	public UserRest getUser(String id) {
		Optional<UserEntity> userEntity = userRepository.findUserByEmail(id);
		if (userEntity.isPresent()) {
			return entityToDTO()
					.andThen(dtoToRest)
					.apply(userEntity.get());
		}
		throw new RuntimeException("User Not found");
	}

	@Override
	public UserRest createUser(UserDetailsRequestModel req) {

		checkIfUserIsAlreadyPresent(req);

		return reqToDTO.andThen(dtoToEntity).andThen(saveUser).andThen(entityToDTO).andThen(dtoToRest).apply(req);

		/*
		 * //Old Code
		 * checkIfUserIsAlreadyPresent(req);
		 * 
		 * UserEntity userEntity = new UserEntity(); userEntity = modelMapper.map(req,
		 * UserEntity.class);
		 * 
		 * String publicUserId = utils.generateUserId(10);
		 * userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(req.getPassword(
		 * ))); userEntity.setUserId(publicUserId);
		 * 
		 * UserEntity storedUser = userRepository.save(userEntity);
		 * 
		 * return modelMapper.map(storedUser, UserRest.class);
		 */

	}

	private void checkIfUserIsAlreadyPresent(UserDetailsRequestModel req) {
		Optional<UserEntity> storedUserDetails = userRepository.findUserByEmail(req.getEmail());

		if (storedUserDetails.isPresent()) {
			throw new RuntimeException("Recored Already Exists");
		}
	}

	/**
	 * Function Model
	 * 
	 * @return
	 */

	private Function<UserDetailsRequestModel, UserDTO> reqToDTO() {
		Function<UserDetailsRequestModel, UserDTO> reqToDTO = r -> userTransformerService.userReqToDto(r);
		return reqToDTO;
	}

	private Function<UserDTO, UserEntity> dtoToEntity() {
		Function<UserDTO, UserEntity> dtoToEntity = r -> {
			UserEntity entity = userTransformerService.userDtoToEntity(r);
			String publicUserId = utils.generateUserId(10);
			entity.setEncryptedPassword(bCryptPasswordEncoder.encode(r.getPassword()));
			entity.setUserId(publicUserId);
			return entity;
		};
		return dtoToEntity;
	}

	private Function<UserEntity, UserDTO> entityToDTO() {
		Function<UserEntity, UserDTO> entityToDTO = r -> userTransformerService.userEntityToDTO(r);
		return entityToDTO;
	}

	private Function<UserDTO, UserRest> dtoToRest() {
		Function<UserDTO, UserRest> dtoToRest = r -> userTransformerService.userDtoToRest(r);
		return dtoToRest;
	}

	private Function<UserEntity, UserEntity> saveUser() {
		Function<UserEntity, UserEntity> saveUser = r -> userRepository.save(r);
		return saveUser;
	}

}

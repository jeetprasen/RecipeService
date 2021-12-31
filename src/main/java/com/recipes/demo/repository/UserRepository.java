package com.recipes.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.recipes.demo.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	
	Optional<UserEntity> findUserByEmail(String email);
	
	Optional<UserEntity> findUserByEmailAndEncryptedPassword(String email, String password);
}

package com.dharuan.springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dharuan.springmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}

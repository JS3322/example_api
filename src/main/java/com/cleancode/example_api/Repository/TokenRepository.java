package com.cleancode.example_api.Repository;

import com.cleancode.example_api.DTO.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<Token, String> {
    Optional<Token> findByTokenValue(String tokenValue);
}

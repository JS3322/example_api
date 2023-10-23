package com.cleancode.example_api.Service;

import com.cleancode.example_api.DTO.Token;
import com.cleancode.example_api.Repository.TokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    private static final String SECRET_KEY = "yourSecretKey"; // Change this to a strong secret key

    public String generateToken() {
        String token = Jwts.builder()
                .setSubject("user") // Customize with user information
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // Token expiration in 1 hour
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        Token tokenEntity = new Token();
        tokenEntity.setTokenValue(token);
        tokenRepository.save(tokenEntity);

        return token;
    }

    public boolean validateToken(String token) {
        Optional<Token> storedToken = tokenRepository.findByTokenValue(token);
        if (storedToken.isPresent()) {
            try {
                Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
                return true;
            } catch (Exception e) {
                // Invalid or expired token
                return false;
            }
        }
        return false;
    }
}

package com.cleancode.example_api.DTO;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Token {
    @Id
    private String id;
    private String tokenValue;
    // getters, setters, ...
}

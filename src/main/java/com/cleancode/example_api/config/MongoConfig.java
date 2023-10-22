package com.cleancode.example_api.config;

import com.cleancode.example_api.CustomUtility.CustomConvert.BigDecimalToDoubleConverter;
import com.cleancode.example_api.CustomUtility.CustomConvert.DoubleToBigDecimalConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import java.util.Arrays;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Override
    public String getDatabaseName() {
        //db 이름
        return "test";
    }

    @Override
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(new BigDecimalToDoubleConverter(), new DoubleToBigDecimalConverter()));
    }
}
package com.cleancode.example_api.Controller.read;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v2")
public class MongoQueryV2Controller {

    private MongoTemplate mongoTemplate;

    MongoQueryV2Controller(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    //TODO [js] use mongo template and call mongodb data group by
    @Operation(summary = "mongoDB group api 예제", description = "req : none // res : none")
    @GetMapping("/query")
    public ResponseEntity<String> executeGroupQuery() {
        JSONArray jsonArray = new JSONArray();

        var groupOperation = Aggregation.group("provider", "product").sum("cost").as("sumCost");

        var projectionOperation = Aggregation.project("sumCost")
                .and("_id.provider").as("provider")
                .and("_id.product").as("product")
                .andExclude("_id");

        var aggregation = Aggregation.newAggregation(groupOperation, projectionOperation);

        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, "test100", Map.class);
        List<Map> resultList = results.getMappedResults();

        for (Map map : resultList) {
            System.out.println(map);
        }

        System.out.println("### calc complete ###");


        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonArray.toString());
    }
}
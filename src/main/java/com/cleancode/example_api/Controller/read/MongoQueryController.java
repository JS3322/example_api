package com.cleancode.example_api.Controller.read;

import com.cleancode.example_api.DTO.RequestDTO;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class MongoQueryController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Operation(summary = "mongoDB group api 예제", description = "req : none // res : none")
    @PostMapping("/group")
    public ResponseEntity<String> executeGroupByQuery(@RequestBody RequestDTO requestDTO) {


        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("test");

    }

    //TODO [js] use mongo template and call mongodb data group by
    @Operation(summary = "mongoDB group api 예제", description = "req : none // res : none")
    @GetMapping("/group")
    public ResponseEntity<String> executeGroupQuery() {
        JSONArray jsonArray = new JSONArray();


        var groupOperation = Aggregation.group("provider", "product").sum("cost").as("sumCost");

        //.and : _id안에 필드를 새로 명시
        //.andExclude : _id필드 삭제
        var projectionOperation = Aggregation.project("sumCost")
                .and("_id.provider").as("provider")
                .and("_id.product").as("product")
                .andExclude("_id");

        // Define the aggregation pipeline with the group operation
        var aggregation = Aggregation.newAggregation(groupOperation, projectionOperation);

        // Run the aggregation pipeline and get the results
        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, "test100", Map.class);
        List<Map> resultList = results.getMappedResults();

        // Print the results
        for (Map map : resultList) {
            System.out.println(map);
        }

        System.out.println("### calc complete ###");


        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonArray.toString());
    }

    //TODO [js] use mongo template and call mongodb data
    @GetMapping("/query")
    public ResponseEntity<String> executeQuery() {

//        log.info("Controller /query : TIME {},  : parameter {}", "new Date()", "test");

        MongoCollection<Document> collection = mongoTemplate.getCollection("test100");

        Document query = new Document("provider", "aws");
        // 예시: 모든 도큐먼트를 조회하는 쿼리
        FindIterable<Document> documents = collection.find(query);

        JSONArray jsonArray = new JSONArray();
        MongoCursor<Document> cursor = documents.iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            jsonArray.put(doc.toJson());
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonArray.toString());
    }

    @PostMapping("/query-post")
    public ResponseEntity<String> executeQuery(@RequestBody RequestDTO requestDTO) {
//        if(!"aws".equalsIgnoreCase(requestDTO.getProvider())) {
//            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("[null]");
//        }

        MongoCollection<Document> collection = mongoTemplate.getCollection("test100");
        Document query = new Document("provider", "aws");

        JSONArray jsonArray = new JSONArray();
        MongoCursor<Document> cursor = collection.find(query).iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            jsonArray.put(doc.toJson());
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonArray.toString());
    }
}
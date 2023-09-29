package com.cleancode.example_api.Controller.read;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MongoQueryController {

    @Autowired
    private MongoTemplate mongoTemplate;

    //TODO [js] use mongo template and call mongodb data
    @GetMapping("/query")
    public ResponseEntity<String> executeQuery() {
        MongoCollection<Document> collection = mongoTemplate.getCollection("TB_INDEX_TEST");

        Document query = new Document("metadata.provider", "aws");
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
    public ResponseEntity<String> executeQuery(@RequestBody RequestPayload payload) {
        if(!"aws".equalsIgnoreCase(payload.getProvider())) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("[null]");
        }

        MongoCollection<Document> collection = mongoTemplate.getCollection("TB_INDEX_TEST");
        Document query = new Document("metadata.provider", "aws");

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

class RequestPayload {
    private String provider;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
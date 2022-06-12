package org.example.controller;

import com.mongodb.bulk.BulkWriteInsert;
import org.example.entity.Documents;
import org.example.entity.DocumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/documents")
public class DocumentsController {

    @Autowired
    DocumentsRepository documentsRepository;

    @Autowired
    MongoTemplate mongoTemplate;


    @PostMapping("")
    public ResponseEntity<Documents> createDocument(@RequestBody Documents documentsRequest) {
        Documents _document = documentsRepository.insert(new Documents(documentsRequest.getTitle(), documentsRequest.getDescription()));
        return new ResponseEntity<>(_document, HttpStatus.CREATED);
    }

    @PostMapping("/using-template")
    public ResponseEntity<List<BulkWriteInsert>> createUsingTemplate(@RequestBody Documents documentsRequest) {
        Documents _document = new Documents(documentsRequest.getTitle(), documentsRequest.getDescription());
        List<Documents> validDocuments = new ArrayList<>();
        validDocuments.add(_document);
        BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Documents.class);
        bulkOperations.insert(validDocuments);
        return new ResponseEntity<>(bulkOperations.execute().getInserts(),HttpStatus.CREATED);
    }
}

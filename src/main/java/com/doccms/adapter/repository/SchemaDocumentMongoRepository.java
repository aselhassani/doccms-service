package com.doccms.adapter.repository;

import java.util.Optional;

import com.doccms.adapter.repository.document.SchemaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchemaDocumentMongoRepository extends MongoRepository<SchemaDocument, Long> {

    Optional<SchemaDocument> findByName(String name);

}

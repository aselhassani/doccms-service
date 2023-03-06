package com.doccms.adapter.repository;

import com.doccms.adapter.repository.document.SchemaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchemaDocumentMongoRepository extends MongoRepository<SchemaDocument, Long> {

}

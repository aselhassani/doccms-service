package com.doccms.adapter.repository.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "node_type")
public record NodeTypeDocument(

    @Id
    @MongoId
    String id,
    String name,
    String description,
    List<FieldDocument> fields

) {


}

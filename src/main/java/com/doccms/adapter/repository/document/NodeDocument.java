package com.doccms.adapter.repository.document;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Builder
@Document(collection = "node")
public record NodeDocument(
    @Id
    @MongoId
    Long id,
    String schema,
    String parentId,
    String title,
    String description,
    String owner,
    Set<String> contributors,
    String language,
    Instant createdAt,
    Instant updatedAt,
    Map<String, Object> properties

) {
    private static final String NODE_SEQUENCE = "schema_sequence";
}

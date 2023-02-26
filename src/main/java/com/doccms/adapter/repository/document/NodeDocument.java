package com.doccms.adapter.repository.document;

import java.time.Instant;
import java.util.Set;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Builder
@Document(collection = "item")
public record NodeDocument(
    @Id
    @MongoId
    String id,
    String parentId,
    String typeName,
    String title,
    String description,
    String owner,
    Set<String> contributors,
    String language,
    Instant createdAt,
    Instant updatedAt

) {
}

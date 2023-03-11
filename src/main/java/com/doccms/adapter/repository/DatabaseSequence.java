package com.doccms.adapter.repository;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
@Builder
public record DatabaseSequence(
    @Id
    String id,
    long seq) {

}
package com.doccms.adapter.repository.document;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.doccms.domain.model.Schema;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Builder
@Document(collection = "schema")
public record SchemaDocument(
    @Id
    @MongoId
    Long id,
    String name,
    String description,
    List<FieldDocument> fields
) {

    private static final String SEQUENCE_NAME = "schema_sequence";

    public static SchemaDocument fromDomain(Schema domain) {
        return SchemaDocument.builder()
                             .id(domain.id())
                             .name(domain.name())
                             .description(domain.description())
                             .fields(Optional.of(domain.fields()).orElse(Collections.emptyList())
                                             .stream()
                                             .map(FieldDocument::fromDomain)
                                             .toList()
                                    )
                             .build();
    }

    public Schema toDomain() {
        return Schema.builder()
                     .id(this.id())
                     .name(this.name())
                     .description(this.description())
                     .fields(Optional.of(this.fields()).orElse(Collections.emptyList())
                                     .stream()
                                     .map(FieldDocument::toDomain)
                                     .toList()
                            )
                     .build();
    }
}

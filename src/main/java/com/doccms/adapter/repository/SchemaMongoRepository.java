package com.doccms.adapter.repository;

import java.util.Optional;

import com.doccms.adapter.repository.document.SchemaDocument;
import com.doccms.domain.model.Schema;
import com.doccms.port.repository.SchemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SchemaMongoRepository implements SchemaRepository {

    private static final String SEQUENCE_NAME = "SCHEMA_SEQ";

    private final SchemaDocumentMongoRepository schemaDocumentMongoRepository;

    private final SequenceGenerator sequenceGenerator;

    @Override
    public Schema save(Schema schema) {
        return Optional.of(schema)
                       .map(SchemaDocument::fromDomain)
                       .map(document -> document.withId(sequenceGenerator.generateSequence(SEQUENCE_NAME)))
                       .map(schemaDocumentMongoRepository::save)
                       .map(SchemaDocument::toDomain)
                       .orElse(null);
    }

    @Override
    public Optional<Schema> findByName(String name) {
        return schemaDocumentMongoRepository.findByName(name)
                                            .map(SchemaDocument::toDomain);
    }
}

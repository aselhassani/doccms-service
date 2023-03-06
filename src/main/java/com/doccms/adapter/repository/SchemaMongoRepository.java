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

    private final SchemaDocumentMongoRepository schemaDocumentMongoRepository;

    @Override
    public Schema save(Schema schema) {
        return Optional.of(schema)
                       .map(SchemaDocument::fromDomain)
                       .map(schemaDocumentMongoRepository::save)
                       .map(SchemaDocument::toDomain)
                       .orElse(null);
    }
}

package com.doccms.port.repository;

import java.util.Optional;

import com.doccms.domain.model.Schema;

public interface SchemaRepository {

    Schema save(Schema schema);

    Optional<Schema> findByName(String name);
}

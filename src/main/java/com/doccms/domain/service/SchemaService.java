package com.doccms.domain.service;


import java.util.Optional;

import com.doccms.domain.model.Schema;
import com.doccms.port.repository.SchemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchemaService {

    private final SchemaRepository schemaRepository;

    public Schema save(Schema schema) {
        return schemaRepository.save(schema);
    }

    public Optional<Schema> findByName(String name) {
        return schemaRepository.findByName(name);
    }
}

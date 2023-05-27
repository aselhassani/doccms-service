package com.doccms.adapter.ws.admin.v1;

import com.doccms.adapter.ws.admin.v1.dto.FieldV1DTO;
import com.doccms.adapter.ws.admin.v1.dto.SchemaRQV1DTO;
import com.doccms.adapter.ws.admin.v1.dto.SchemaRSV1DTO;
import com.doccms.adapter.ws.admin.v1.exception.InvalidConstraintsException;
import com.doccms.adapter.ws.admin.v1.exception.InvalidFieldValueException;
import com.doccms.adapter.ws.admin.v1.exception.SchemaNameExistsException;
import com.doccms.adapter.ws.admin.v1.exception.SchemaValidator;
import com.doccms.domain.service.SchemaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/schemas")
@RequiredArgsConstructor
public class SchemaControllerV1 {

    private final SchemaService schemaService;

    private final SchemaValidator schemaValidator;

    @PostMapping
    public ResponseEntity<SchemaRSV1DTO> createSchema(
            @RequestBody @Valid @NotNull SchemaRQV1DTO schemaRQV1DTO) {

        validateCreation(schemaRQV1DTO);

        return Optional.of(schemaRQV1DTO)
                .map(SchemaRQV1DTO::toDomain)
                .map(schemaService::save)
                .map(SchemaRSV1DTO::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(null);
    }

    void validateCreation(SchemaRQV1DTO schemaRQV1DTO) {
        fieldsDefaultValuesAreValid(schemaRQV1DTO.fields());
        fieldsConstraintsAreValid(schemaRQV1DTO.fields());
        schemaNameNotDoesNotExist(schemaRQV1DTO.name());
    }

    private void fieldsConstraintsAreValid(List<FieldV1DTO> fields) {
        fields.stream()
                .filter(f -> !schemaValidator.validateConstraints(f.constraints(), f.type(), f.mode()))
                .forEach(f -> {
                    throw new InvalidConstraintsException(f.type(), f.mode(), f.constraints());
                });
    }

    private void fieldsDefaultValuesAreValid(List<FieldV1DTO> fields) {
        fields.stream()
                .filter(f -> !schemaValidator.validateFieldValue(f.defaultValue(), f.type(), f.mode()))
                .forEach(f -> {
                    throw new InvalidFieldValueException(f.type(), f.mode(), f.defaultValue());
                });
    }

    private void schemaNameNotDoesNotExist(String name) {
        schemaService.findByName(name).ifPresent(schema -> {
            throw new SchemaNameExistsException(name);
        });
    }


}

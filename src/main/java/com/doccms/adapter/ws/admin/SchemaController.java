package com.doccms.adapter.ws.admin;

import com.doccms.adapter.ws.FieldValidator;
import com.doccms.adapter.ws.admin.dto.FieldDTO;
import com.doccms.adapter.ws.admin.dto.SchemaDTO;
import com.doccms.adapter.ws.admin.exception.InvalidConstraintsException;
import com.doccms.adapter.ws.admin.exception.InvalidFieldValueException;
import com.doccms.adapter.ws.admin.exception.SchemaNameExistsException;
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
public class SchemaController {

    private final SchemaService schemaService;

    private final FieldValidator schemaValidator;

    @PostMapping
    public ResponseEntity<SchemaDTO> createSchema(
            @RequestBody @Valid @NotNull SchemaDTO schemaRQV1DTO) {

        validateCreation(schemaRQV1DTO);

        return Optional.of(schemaRQV1DTO)
                .map(SchemaDTO::toDomain)
                .map(schemaService::save)
                .map(SchemaDTO::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(null);
    }

    void validateCreation(SchemaDTO schemaRQV1DTO) {
        fieldsDefaultValuesAreValid(schemaRQV1DTO.fields());
        fieldsConstraintsAreValid(schemaRQV1DTO.fields());
        schemaNameNotDoesNotExist(schemaRQV1DTO.name());
    }

    private void fieldsConstraintsAreValid(List<FieldDTO> fields) {
        fields.stream()
                .filter(f -> !schemaValidator.validateConstraints(f.constraints(), f.type(), f.mode()))
                .forEach(f -> {
                    throw new InvalidConstraintsException(f.type(), f.mode(), f.constraints());
                });
    }

    private void fieldsDefaultValuesAreValid(List<FieldDTO> fields) {
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

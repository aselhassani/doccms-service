package com.doccms.adapter.ws.admin;

import java.util.Optional;

import com.doccms.adapter.ws.admin.dto.SchemaRequestV1DTO;
import com.doccms.adapter.ws.admin.dto.SchemaResponseV1DTO;
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

@Log4j2
@RestController
@RequestMapping("/schemas")
@RequiredArgsConstructor
public class SchemaController {

    private final SchemaService schemaService;

    @PostMapping
    public ResponseEntity<SchemaResponseV1DTO> createSchema(
        @RequestBody @Valid @NotNull SchemaRequestV1DTO schemaV1DTO) {

        var name = schemaV1DTO.name();
        schemaService.findByName(name).ifPresent(schema -> {
            throw new SchemaNameExistsException(name);
        });

        return Optional.of(schemaV1DTO)
                       .map(SchemaRequestV1DTO::toDomain)
                       .map(schemaService::save)
                       .map(SchemaResponseV1DTO::fromDomain)
                       .map(ResponseEntity::ok)
                       .orElse(null);
    }


}

package com.doccms.adapter.ws;

import java.util.Optional;

import com.doccms.adapter.ws.dto.SchemaRequestV1DTO;
import com.doccms.adapter.ws.dto.SchemaResponseV1DTO;
import com.doccms.domain.service.SchemaService;
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
public class SchemaControllerV1 {

    private final SchemaService schemaService;

    @PostMapping
    public ResponseEntity<SchemaResponseV1DTO> createSchema(@RequestBody SchemaRequestV1DTO schemaV1DTO) {
        return Optional.of(schemaV1DTO)
                       .map(SchemaRequestV1DTO::toDomain)
                       .map(schemaService::save)
                       .map(SchemaResponseV1DTO::fromDomain)
                       .map(ResponseEntity::ok)
                       .orElse(null);
    }


}

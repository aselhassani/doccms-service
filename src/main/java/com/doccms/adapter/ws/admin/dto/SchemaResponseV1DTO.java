package com.doccms.adapter.ws.admin.dto;

import java.util.List;

import com.doccms.domain.model.Schema;
import lombok.Builder;

@Builder
@io.swagger.v3.oas.annotations.media.Schema(name = "SchemaResponseV1DTO", description = "Schema response DTO")
public record SchemaResponseV1DTO(
    Long id,
    String name,
    String description,
    List<FieldV1DTO> fields
) {

    public static SchemaResponseV1DTO fromDomain(Schema nodeType) {
        return SchemaResponseV1DTO.builder()
                                  .id(nodeType.id())
                                  .name(nodeType.name())
                                  .description(nodeType.description())
                                  .fields(nodeType.fields().stream().map(FieldV1DTO::fromDomain).toList())
                                  .build();
    }

    public Schema toDomain() {
        return Schema.builder()
                     .id(this.id())
                     .name(this.name())
                     .description(this.description())
                     .fields(this.fields().stream().map(FieldV1DTO::toDomain).toList())
                     .build();
    }

}

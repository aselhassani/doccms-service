package com.doccms.ws.dto;

import java.util.List;

public record NodeTypeV1DTO(
    String id,
    String name,
    String description,
    List<FieldV1DTO> fields
) {

}

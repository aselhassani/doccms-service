package com.doccms.adapter.ws.dto;

import lombok.Builder;

@Builder
public record ErrorDTO(
    String id,
    String code,
    String message) {


}


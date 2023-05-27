package com.doccms.adapter.ws.admin.dto;

import lombok.Builder;

@Builder
public record ErrorDTO(
        String id,
        String code,
        String message) {


}


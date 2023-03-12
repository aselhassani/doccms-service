package com.doccms.adapter.ws.admin.v1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SchemaNameExistsException extends RuntimeException {

    private String schemaName;

    @Override
    public String getMessage() {
        return "Schema name already exists : " + schemaName;
    }
}

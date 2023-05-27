package com.doccms.adapter.ws.admin.v1.exception;

import com.doccms.adapter.ws.admin.v1.dto.ConstraintsV1DTO;
import com.doccms.adapter.ws.admin.v1.dto.enums.FieldMode;
import com.doccms.adapter.ws.admin.v1.dto.enums.FieldType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidConstraintsException extends RuntimeException {

    private FieldType type;
    private FieldMode mode;
    private ConstraintsV1DTO constraints;

    @Override
    public String getMessage() {
        return "Invalid constraint : " + constraints + " for type : " + type + " and mode : " + mode;
    }
}

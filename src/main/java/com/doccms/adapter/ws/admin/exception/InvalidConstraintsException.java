package com.doccms.adapter.ws.admin.exception;

import com.doccms.adapter.ws.admin.dto.ConstraintsDTO;
import com.doccms.adapter.ws.admin.dto.enums.FieldMode;
import com.doccms.adapter.ws.admin.dto.enums.FieldType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidConstraintsException extends RuntimeException {

    private FieldType type;
    private FieldMode mode;
    private ConstraintsDTO constraints;

    @Override
    public String getMessage() {
        return "Invalid constraint : " + constraints + " for type : " + type + " and mode : " + mode;
    }
}

package com.doccms.adapter.ws.admin.exception;

import com.doccms.adapter.ws.admin.dto.enums.FieldMode;
import com.doccms.adapter.ws.admin.dto.enums.FieldType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidFieldValueException extends RuntimeException {

    private FieldType type;
    private FieldMode mode;
    private Object rejectedValue;

    @Override
    public String getMessage() {
        return "Invalid field value : " + rejectedValue + " for type : " + type + " and mode : " + mode;
    }

}

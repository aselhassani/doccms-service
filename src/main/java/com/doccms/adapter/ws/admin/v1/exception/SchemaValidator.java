package com.doccms.adapter.ws.admin.v1.exception;

import com.doccms.adapter.ws.admin.v1.dto.ConstraintsV1DTO;
import com.doccms.adapter.ws.admin.v1.dto.enums.FieldMode;
import com.doccms.adapter.ws.admin.v1.dto.enums.FieldType;
import org.springframework.stereotype.Service;

@Service
public class SchemaValidator {

    public boolean validateFieldValue(Object value, FieldType type, FieldMode mode) {
        return false;
    }

    public boolean validateConstraints(ConstraintsV1DTO constraints, FieldType type, FieldMode mode) {
        return false;
    }

}

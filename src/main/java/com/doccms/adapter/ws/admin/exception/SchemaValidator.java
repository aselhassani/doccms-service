package com.doccms.adapter.ws.admin.exception;

import com.doccms.adapter.ws.admin.dto.enums.FieldMode;
import com.doccms.adapter.ws.admin.dto.ConstraintsDTO;
import com.doccms.adapter.ws.admin.dto.enums.FieldType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchemaValidator {

    public boolean validateFieldValue(Object value, FieldType type, FieldMode mode) {
        if (value == null) return true;
        return switch (mode) {
            case SINGLE -> validateSingleValue(value, type);
            case LIST -> validateListOfValues(value, type);
        };
    }

    private boolean validateSingleValue(Object value, FieldType type) {
        return switch (type) {
            case BOOLEAN -> validateBoolean(value);
            case INTEGER -> false;
            case LONG -> false;
            case FLOAT -> false;
            case DOUBLE -> false;
            case DATE -> false;
            case STRING -> false;
        };
    }

    private boolean validateBoolean(Object value) {
        return value instanceof Boolean;
    }

    private boolean validateListOfValues(Object value, FieldType type) {
        if (!(value instanceof List)) return false;
        var values = ((List<?>) value).stream();
        return switch (type) {
            case BOOLEAN -> values.allMatch(this::validateBoolean);
            case INTEGER -> false;
            case LONG -> false;
            case FLOAT -> false;
            case DOUBLE -> false;
            case DATE -> false;
            case STRING -> false;
        };
    }

    public boolean validateConstraints(ConstraintsDTO constraints, FieldType type, FieldMode mode) {
        return false;
    }

}

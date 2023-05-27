package com.doccms.helpers;


import com.doccms.adapter.ws.admin.dto.*;
import com.doccms.adapter.ws.admin.dto.enums.FieldMode;
import com.doccms.adapter.ws.admin.dto.enums.FieldType;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static com.doccms.helpers.TestHelper.getRandomId;
import static com.doccms.helpers.TestHelper.getRandomText;

public class DtoTestHelper {

    private static final Random random = new SecureRandom();

    public static SchemaDTO getRandomSchemaRequestV1DTO(String name, FieldDTO fieldV1DTO) {
        return SchemaDTO.builder()
                .name(name)
                .description(getRandomText(20))
                .fields(List.of(fieldV1DTO))
                .build();
    }

    public static FieldDTO getRandomFieldV1DTO() {
        return getRandomFieldV1DTO(getRandomFieldType(), getRandomFieldMode());
    }

    private static FieldType getRandomFieldType() {
        return TestHelper.getRandomClass(FieldType.class);
    }

    private static FieldMode getRandomFieldMode() {
        return TestHelper.getRandomClass(FieldMode.class);
    }

    private static FieldDTO getRandomFieldV1DTO(FieldType type, FieldMode mode) {
        var constraint = getRandomConstraintV1DTO(type);
        var defaultValue = getRandomValue(type);
        return FieldDTO.builder()
                .name("f" + getRandomId())
                .type(type)
                .mode(mode)
                .defaultValue(mode.equals(FieldMode.SINGLE) ? defaultValue : List.of(defaultValue))
                .constraints(mode.equals(FieldMode.SINGLE) ? constraint : constraint.withListConstraint(getRandomListConstraint(type)))
                .build();
    }

    private static Object getRandomValue(FieldType type) {
        return switch (type) {
            case BOOLEAN -> random.nextBoolean();
            case INTEGER -> random.nextInt();
            case LONG -> random.nextLong();
            case DATE -> LocalDate.now();
            case FLOAT -> random.nextFloat();
            case DOUBLE -> random.nextDouble();
            case STRING -> TestHelper.getRandomText(20);
        };
    }

    private static ConstraintsDTO getRandomConstraintV1DTO(FieldType type) {
        var constraints = ConstraintsDTO.builder()
                .nullable(random.nextBoolean())
                .build();
        return switch (type) {
            case BOOLEAN -> constraints.withNullable(random.nextBoolean());
            case INTEGER, LONG, FLOAT, DOUBLE -> constraints.withNumberConstraint(getRandomNumberConstraint());
            case STRING -> constraints.withSizeConstraint(getRandomSizeConstraint());
            case DATE -> constraints.withDateConstraint(getRandomDateConstraint());
        };
    }

    private static ListConstraintDTO getRandomListConstraint(FieldType type) {
        return ListConstraintDTO.builder()
                .minSize(random.nextInt(1, 10))
                .maxSize(random.nextInt(10, 100))
                .possibleValues(List.of(getRandomValue(type)))
                .build();
    }

    private static DateConstraintDTO getRandomDateConstraint() {
        var start = LocalDate.now();
        return DateConstraintDTO.builder()
                .after(start)
                .before(start.plusDays(30))
                .build();
    }

    private static NumberConstraintDTO getRandomNumberConstraint() {
        return NumberConstraintDTO.builder()
                .minValue(1d)
                .maxValue(99d)
                .build();
    }

    private static SizeConstraintDTO getRandomSizeConstraint() {
        return SizeConstraintDTO.builder()
                .minSize(random.nextInt(1, 10))
                .maxSize(random.nextInt(10, 99))
                .build();
    }


}

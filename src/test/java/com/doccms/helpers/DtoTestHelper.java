package com.doccms.helpers;


import com.doccms.adapter.ws.admin.v1.dto.*;
import com.doccms.adapter.ws.admin.v1.dto.enums.FieldMode;
import com.doccms.adapter.ws.admin.v1.dto.enums.FieldType;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static com.doccms.helpers.TestHelper.getRandomId;
import static com.doccms.helpers.TestHelper.getRandomText;

public class DtoTestHelper {

    private static final Random random = new SecureRandom();

    public static SchemaRQV1DTO getRandomSchemaRequestV1DTO(String name, FieldV1DTO fieldV1DTO) {
        return SchemaRQV1DTO.builder()
                .name(name)
                .description(getRandomText(20))
                .fields(List.of(fieldV1DTO))
                .build();
    }

    public static FieldV1DTO getRandomFieldV1DTO() {
        return getRandomFieldV1DTO(getRandomFieldType(), getRandomFieldMode());
    }

    private static FieldType getRandomFieldType() {
        return TestHelper.getRandomClass(FieldType.class);
    }

    private static FieldMode getRandomFieldMode() {
        return TestHelper.getRandomClass(FieldMode.class);
    }

    private static FieldV1DTO getRandomFieldV1DTO(FieldType type, FieldMode mode) {
        var constraint = getRandomConstraintV1DTO(type);
        var defaultValue = getRandomValue(type);
        return FieldV1DTO.builder()
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

    private static ConstraintsV1DTO getRandomConstraintV1DTO(FieldType type) {
        var constraints = ConstraintsV1DTO.builder()
                .nullable(random.nextBoolean())
                .build();
        return switch (type) {
            case BOOLEAN -> constraints.withNullable(random.nextBoolean());
            case INTEGER, LONG, FLOAT, DOUBLE -> constraints.withNumberConstraint(getRandomNumberConstraint());
            case STRING -> constraints.withSizeConstraint(getRandomSizeConstraint());
            case DATE -> constraints.withDateConstraint(getRandomDateConstraint());
        };
    }

    private static ListConstraintV1DTO getRandomListConstraint(FieldType type) {
        return ListConstraintV1DTO.builder()
                .minSize(random.nextInt(1, 10))
                .maxSize(random.nextInt(10, 100))
                .possibleValues(List.of(getRandomValue(type)))
                .build();
    }

    private static DateConstraintV1DTO getRandomDateConstraint() {
        var start = LocalDate.now();
        return DateConstraintV1DTO.builder()
                .after(start)
                .before(start.plusDays(30))
                .build();
    }

    private static NumberConstraintV1DTO getRandomNumberConstraint() {
        return NumberConstraintV1DTO.builder()
                .minValue(1d)
                .maxValue(99d)
                .build();
    }

    private static SizeConstraintV1DTO getRandomSizeConstraint() {
        return SizeConstraintV1DTO.builder()
                .minSize(random.nextInt(1, 10))
                .maxSize(random.nextInt(10, 99))
                .build();
    }


}

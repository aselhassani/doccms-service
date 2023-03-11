package com.doccms.helpers;


import static com.doccms.helpers.TestHelper.getRandomClass;
import static com.doccms.helpers.TestHelper.getRandomId;
import static com.doccms.helpers.TestHelper.getRandomText;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import com.doccms.adapter.ws.admin.dto.ConstraintsV1DTO;
import com.doccms.adapter.ws.admin.dto.DateConstraintV1DTO;
import com.doccms.adapter.ws.admin.dto.FieldV1DTO;
import com.doccms.adapter.ws.admin.dto.ListConstraintV1DTO;
import com.doccms.adapter.ws.admin.dto.NumberConstraintV1DTO;
import com.doccms.adapter.ws.admin.dto.SchemaRequestV1DTO;
import com.doccms.adapter.ws.admin.dto.SizeConstraintV1DTO;
import com.doccms.adapter.ws.admin.dto.enums.FieldMode;
import com.doccms.adapter.ws.admin.dto.enums.FieldType;

public class DtoTestHelper {

    private static final Random random = new SecureRandom();

    public static SchemaRequestV1DTO getRandomSchemaRequestV1DTO(String name) {
        return SchemaRequestV1DTO.builder()
                                 .name(name)
                                 .description(getRandomText(20))
                                 .fields(List.of(getRandomFieldV1DTO()))
                                 .build();
    }

    private static FieldV1DTO getRandomFieldV1DTO() {
        return getRandomFieldV1DTO(getRandomClass(FieldType.class), getRandomClass(FieldMode.class),
                                   getRandomConstraintsV1DTO());
    }

    private static FieldV1DTO getRandomFieldV1DTO(FieldType type, FieldMode mode, ConstraintsV1DTO constraints) {
        return FieldV1DTO.builder()
                         .name(getRandomId("field"))
                         .type(type)
                         .mode(mode)
                         .constraints(constraints)
                         .defaultValue(getRandomText(32))
                         .build();
    }

    private static ConstraintsV1DTO getRandomConstraintsV1DTO() {
        var date = LocalDate.now();
        return ConstraintsV1DTO.builder()
                               .nullable(false)
                               .sizeConstraint(SizeConstraintV1DTO
                                                   .builder()
                                                   .minSize(1)
                                                   .maxSize(32)
                                                   .build())
                               .dateConstraint(DateConstraintV1DTO
                                                   .builder()
                                                   .after(date.minusDays(30))
                                                   .before(date.plusDays(30))
                                                   .build())
                               .numberConstraint(NumberConstraintV1DTO
                                                     .builder()
                                                     .minValue(0.0)
                                                     .maxValue(random.nextDouble(0, 99999))
                                                     .build())
                               .listConstraint(ListConstraintV1DTO
                                                   .builder()
                                                   .maxSize(10)
                                                   .possibleValues(TestHelper.getRandomListOfStrings())
                                                   .minSize(1)
                                                   .build())
                               .pattern(getRandomText(3))
                               .build();
    }


}

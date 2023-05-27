package com.doccms.helpers;


import static com.doccms.helpers.TestHelper.getRandomClass;
import static com.doccms.helpers.TestHelper.getRandomId;
import static com.doccms.helpers.TestHelper.getRandomText;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import com.doccms.domain.model.Field;
import com.doccms.domain.model.Schema;
import com.doccms.domain.model.constraint.Constraints;
import com.doccms.domain.model.constraint.DateConstraint;
import com.doccms.domain.model.constraint.ListConstraint;
import com.doccms.domain.model.constraint.NumberConstraint;
import com.doccms.domain.model.constraint.SizeConstraint;
import com.doccms.domain.model.enums.FieldMode;
import com.doccms.domain.model.enums.FieldType;

public class DomainTestHelper {

    private static final Random random = new SecureRandom();

    public static Schema getRandomSchema() {
        return getRandomSchema(getRandomId("sch"));
    }

    public static Schema getRandomSchema(String name) {
        return Schema.builder()
                     .id(getRandomId())
                     .name(name)
                     .description(getRandomText(20))
                     .fields(List.of(getRandomField()))
                     .build();
    }

    private static Field getRandomField() {
        return getRandomField(getRandomClass(FieldType.class), getRandomClass(FieldMode.class), getRandomConstraints());
    }

    private static Field getRandomField(FieldType type, FieldMode mode, Constraints constraints) {
        return Field.builder()
                    .name(getRandomId("field"))
                    .type(type)
                    .mode(mode)
                    .constraints(constraints)
                    .defaultValue(getRandomText(32))
                    .build();
    }

    private static Constraints getRandomConstraints() {
        var date = LocalDate.now();
        return Constraints.builder()
                          .nullable(false)
                          .sizeConstraint(SizeConstraint
                                              .builder()
                                              .minSize(1)
                                              .maxSize(32)
                                              .build())
                          .dateConstraint(DateConstraint
                                              .builder()
                                              .after(date.minusDays(30))
                                              .before(date.plusDays(30))
                                              .build())
                          .numberConstraint(NumberConstraint
                                                .builder()
                                                .minValue(0.0)
                                                .maxValue(random.nextDouble(0, 99999))
                                                .build())
                          .listConstraint(ListConstraint
                                              .builder()
                                              .maxSize(10)
                                              .possibleValues(TestHelper.getRandomListOfStrings())
                                              .minSize(1)
                                              .build())
                          .pattern(getRandomText(3))
                          .build();
    }
}

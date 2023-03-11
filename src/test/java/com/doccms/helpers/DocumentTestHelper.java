package com.doccms.helpers;

import static com.doccms.helpers.TestHelper.getRandomClass;
import static com.doccms.helpers.TestHelper.getRandomId;
import static com.doccms.helpers.TestHelper.getRandomText;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import com.doccms.adapter.repository.document.FieldDocument;
import com.doccms.adapter.repository.document.SchemaDocument;
import com.doccms.adapter.repository.document.constraint.ConstraintsDocument;
import com.doccms.adapter.repository.document.constraint.DateConstraintDocument;
import com.doccms.adapter.repository.document.constraint.ListConstraintDocument;
import com.doccms.adapter.repository.document.constraint.NumberConstraintDocument;
import com.doccms.adapter.repository.document.constraint.SizeConstraintDocument;
import com.doccms.adapter.repository.document.enums.FieldMode;
import com.doccms.adapter.repository.document.enums.FieldType;

public class DocumentTestHelper {
    private static final Random random = new SecureRandom();

    public static SchemaDocument getRandomSchemaDocument(String name) {
        return SchemaDocument.builder()
                             .id(getRandomId())
                             .name(name)
                             .description(getRandomText(20))
                             .fields(List.of(getRandomFieldDocument()))
                             .build();
    }

    public static SchemaDocument getRandomSchemaDocument() {
        return getRandomSchemaDocument(getRandomId("sch"));
    }

    private static FieldDocument getRandomFieldDocument() {
        return getRandomFieldDocument(getRandomClass(FieldType.class), getRandomClass(FieldMode.class),
                                      getRandomConstraintsDocument());
    }

    private static FieldDocument getRandomFieldDocument(FieldType type, FieldMode mode,
                                                        ConstraintsDocument constraints) {
        return FieldDocument.builder()
                            .name(getRandomId("field"))
                            .type(type)
                            .mode(mode)
                            .constraints(constraints)
                            .defaultValue(getRandomText(32))
                            .build();
    }

    private static ConstraintsDocument getRandomConstraintsDocument() {
        var date = LocalDate.now();
        return ConstraintsDocument.builder()
                                  .nullable(false)
                                  .sizeConstraint(SizeConstraintDocument
                                                      .builder()
                                                      .minSize(1)
                                                      .maxSize(32)
                                                      .build())
                                  .dateConstraint(DateConstraintDocument
                                                      .builder()
                                                      .after(date.minusDays(30))
                                                      .before(date.plusDays(30))
                                                      .build())
                                  .numberConstraint(NumberConstraintDocument
                                                        .builder()
                                                        .minValue(0.0)
                                                        .maxValue(random.nextDouble(0, 99999))
                                                        .build())
                                  .listConstraint(ListConstraintDocument
                                                      .builder()
                                                      .maxSize(10)
                                                      .possibleValues(TestHelper.getRandomListOfStrings())
                                                      .minSize(1)
                                                      .build())
                                  .pattern(getRandomText(3))
                                  .build();
    }
}

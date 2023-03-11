package com.doccms.adapter.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.doccms.helpers.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;

@ExtendWith(MockitoExtension.class)
class SequenceGeneratorTest {
    @Mock
    MongoOperations mongoOperations;
    @InjectMocks
    private SequenceGenerator underTest;
    private String seqName;

    private DatabaseSequence databaseSequence;

    private Long nextVal;

    @BeforeEach
    void setup() {
        seqName = TestHelper.getRandomText(20);
        nextVal = TestHelper.getRandomId();
        databaseSequence = DatabaseSequence.builder()
                                           .id(seqName)
                                           .seq(nextVal)
                                           .build();
    }


    @Test
    void generateSequenceIncrementDatabaseSequence() {

        when(mongoOperations.findAndModify(any(Query.class), any(UpdateDefinition.class),
                                           any(FindAndModifyOptions.class), any())).thenReturn(databaseSequence);


        var result = underTest.generateSequence(seqName);

        var qryCaptor = ArgumentCaptor.forClass(Query.class);
        var defCaptor = ArgumentCaptor.forClass(Update.class);
        var optCaptor = ArgumentCaptor.forClass(FindAndModifyOptions.class);
        var classCaptor = ArgumentCaptor.forClass(Class.class);

        verify(mongoOperations).findAndModify(qryCaptor.capture(), defCaptor.capture(), optCaptor.capture(),
                                              classCaptor.capture());

        assertThat(qryCaptor.getValue()).usingRecursiveComparison().isEqualTo(query(where("_id").is(seqName)));
        assertThat(defCaptor.getValue()).usingRecursiveComparison().isEqualTo(new Update().inc("seq", 1));
        assertThat(optCaptor.getValue()).usingRecursiveComparison().isEqualTo(options().returnNew(true).upsert(true));
        assertThat(classCaptor.getValue()).isEqualTo(DatabaseSequence.class);
        assertThat(result).isEqualTo(nextVal);
    }
}

package com.co.macla.retotecnico.infrastructure.driven_adapter.mongo;

import com.co.macla.retotecnico.domain.model.transaction.TransactionDto;
import com.co.macla.retotecnico.domain.model.transaction.gateways.TransactionGateway;
import com.co.macla.retotecnico.infrastructure.driven_adapter.mongo.helper.MongoDBAdapter;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.out;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@Slf4j
@Repository
public class MongoDBRepositoryImp extends MongoDBAdapter<MongoDBRepository> implements TransactionGateway {

    private static final String TIMESTAMP = "timestamp";
    private static final String AMOUNT = "amount";
    private static final String TOTAL_AMOUNT = "totalAmount";
    private static final String FORMATED_DATE = "formatedDate";

    public MongoDBRepositoryImp(MongoDBRepository repository, ObjectMapper mapper, MongoTemplate mongoTemplate) {
        super(repository, mapper, mongoTemplate);
    }

    @Override
    public TransactionDto insert(TransactionDto transactionDto) {
        try {
            TransactionEntity transactionEntityIn = this.mapper.map(transactionDto, TransactionEntity.class);
            return this.mapper.map(this.repository.insert(transactionEntityIn), TransactionDto.class);
        } catch (Exception e) {
            log.error("Error saving the transaction in DB, cause: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void aggregate() {
        try {
            ProjectionOperation firstProjection = Aggregation.project(AMOUNT)
                    .and(DateOperators.dateOf(TIMESTAMP).toString("%Y-%m-%d")).as(FORMATED_DATE);

            GroupOperation group = Aggregation.group(FORMATED_DATE)
                    .sum(AMOUNT).as(TOTAL_AMOUNT);

            ProjectionOperation secondProjection = Aggregation.project(FORMATED_DATE, TOTAL_AMOUNT)
                    .and("_id").as(TIMESTAMP)
                    .andExclude("_id");

            AggregationOperation out = out("transactions_per_day");


            // Construcción de la agregación

            //INFO: This method build the aggregation the different method.
            //Aggregation inlineAggregation = newAggregation(firstProjection, group, secondProjection, out);
            Aggregation aggregation = newAggregation(firstProjection, group, secondProjection, out);

            // Ejecución de la agregación
            mongoTemplate.aggregate(aggregation, "transactions", Document.class);
        } catch (Exception e) {
            log.error("Error grouping the transaction and summing its amount, cause: {}", e.getMessage());
            throw e;
        }
    }

    private Aggregation inlineAggregation() {
        return newAggregation(project(AMOUNT)
                        .and(DateOperators.dateOf(TIMESTAMP).toString("%Y-%m-%d")).as(FORMATED_DATE),
                group(FORMATED_DATE).sum(AMOUNT).as(TOTAL_AMOUNT),
                project(FORMATED_DATE, TOTAL_AMOUNT)
                        .and("_id").as(TIMESTAMP)
                        .andExclude("_id"));
    }
}

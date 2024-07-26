package com.co.macla.retotecnico.infrastructure.driven_adapter.mongo;

import com.co.macla.retotecnico.domain.model.transaction.gateways.TransactionGateway;
import com.co.macla.retotecnico.infrastructure.driven_adapter.mongo.helper.MongoDBAdapter;
import org.springframework.stereotype.Repository;

@Repository
public class MongoDBRepositoryImp extends MongoDBAdapter<MongoDBRepository> implements TransactionGateway {

}

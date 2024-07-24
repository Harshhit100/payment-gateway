package com.pay.dto.mapper;

import com.pay.dto.model.request.TransactionRequest;
import com.pay.model.Transaction;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-24T16:27:17+0530",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.0.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class TransactionRequestMapperImpl extends TransactionRequestMapper {

    @Override
    public Transaction toEntity(TransactionRequest dto) {
        if ( dto == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        if ( dto.getCreatedAt() != null ) {
            transaction.setCreatedAt( dto.getCreatedAt() );
        }
        else {
            transaction.setCreatedAt( java.time.Instant.now() );
        }
        transaction.setId( dto.getId() );
        transaction.setAmount( dto.getAmount() );
        transaction.setDescription( dto.getDescription() );

        transaction.setStatus( com.pay.model.Status.SUCCESS );
        transaction.setReferenceNumber( java.util.UUID.randomUUID() );

        setToEntityFields( transaction, dto );

        return transaction;
    }

    @Override
    public TransactionRequest toDto(Transaction entity) {
        if ( entity == null ) {
            return null;
        }

        TransactionRequest transactionRequest = new TransactionRequest();

        transactionRequest.setId( entity.getId() );
        transactionRequest.setAmount( entity.getAmount() );
        transactionRequest.setDescription( entity.getDescription() );
        transactionRequest.setCreatedAt( entity.getCreatedAt() );
        if ( entity.getReferenceNumber() != null ) {
            transactionRequest.setReferenceNumber( entity.getReferenceNumber().toString() );
        }
        transactionRequest.setStatus( entity.getStatus() );

        return transactionRequest;
    }
}

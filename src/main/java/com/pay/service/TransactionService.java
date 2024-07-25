package com.pay.service;

import com.pay.dto.mapper.TransactionRequestMapper;
import com.pay.dto.mapper.TransactionResponseMapper;
import com.pay.dto.model.request.TransactionRequest;
import com.pay.dto.model.response.CommandResponse;
import com.pay.dto.model.response.TransactionResponse;
import com.pay.model.Transaction;
import com.pay.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionRequestMapper transactionRequestMapper;

    private final TransactionResponseMapper transactionResponseMapper;

    @Transactional
    public TransactionResponse findById(Long id) {
        return transactionRepository.findById(id)
                .map(transactionResponseMapper::toDto)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public TransactionResponse findByReferenceNumber(UUID referenceNumber) {
        return transactionRepository.findByReferenceNumber(referenceNumber)
                .map(transactionResponseMapper::toDto)
                .orElseThrow(RuntimeException::new);
   }

   @Transactional
    public List<TransactionResponse> findAllByUserId(Long userId) {
        final List<Transaction> response = transactionRepository.findAllByUserId(userId);
        if(CollectionUtils.isEmpty(response)) {
            throw new RuntimeException();
        }

        return response.stream().map(transactionResponseMapper::toDto)
                .toList();
    }

    @Transactional
    public Page<TransactionResponse> findAll(Pageable pageable)  {
        final Page<Transaction> transactions = transactionRepository.findAll(pageable);
        if(transactions.isEmpty()) {
            throw new RuntimeException();
        }
        return transactions.map(transactionResponseMapper::toDto);
    }

    public CommandResponse create(TransactionRequest request) {
        final Transaction transaction = transactionRequestMapper.toEntity(request);
        transactionRepository.save(transaction);
        return CommandResponse.builder().id(transaction.getId()).build();
    }
}

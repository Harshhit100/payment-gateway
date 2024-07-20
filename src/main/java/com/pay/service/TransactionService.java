package com.pay.service;

import com.pay.dto.model.response.TransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    public TransactionResponse findById(Long id) {
        return null;
    }

    public TransactionResponse findByReferenceNumber(String referenceNumber) {
        return null;
    }

    public Page<TransactionResponse> findAllByUserId(Long userId) {
        return null;
    }

    public Page<TransactionResponse> findAll(Pageable pageable) {
        return null;
    }
}

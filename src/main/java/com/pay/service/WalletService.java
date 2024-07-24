package com.pay.service;

import com.pay.dto.mapper.WalletRequestMapper;
import com.pay.dto.mapper.WalletResponseMapper;
import com.pay.dto.mapper.WalletTransactionRequestMapper;
import com.pay.dto.model.request.TransactionRequest;
import com.pay.dto.model.request.WalletRequest;
import com.pay.dto.model.response.CommandResponse;
import com.pay.dto.model.response.WalletResponse;
import com.pay.model.Wallet;
import com.pay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final WalletRequestMapper walletRequestMapper;
    private final WalletResponseMapper walletResponseMapper;
    private final TransactionService transactionService;
    private final WalletTransactionRequestMapper walletTransactionRequestMapper;

    @Transactional
    public WalletResponse findById(Long id) {
        return walletRepository.findById(id)
                .map(walletResponseMapper::toDto)
                .orElseThrow(() -> new RuntimeException());

    }

    @Transactional
    public WalletResponse findByBankAccountNumber(String bankAccountNumber) {
        return walletRepository.findByBankAccountNumber(bankAccountNumber)
                .map(walletResponseMapper::toDto)
                .orElseThrow(() -> new RuntimeException());
    }

    @Transactional
    public List<WalletResponse> findByUserId(Long userId) {
        return walletRepository.findByUserId(userId).stream()
                .map(walletResponseMapper::toDto)
                .toList();
    }

    public Wallet getByBankAccountNumber(String bankAccountNumber) {
        return walletRepository.findByBankAccountNumber(bankAccountNumber)
                .orElseThrow(() -> new RuntimeException());
    }

    @Transactional
    public Page<WalletResponse> findAll(Pageable pageable) {
        Page<Wallet> wallets = walletRepository.findAll(pageable);
        if(wallets.isEmpty()) {
            throw new RuntimeException();
        }

        return wallets.map(walletResponseMapper::toDto);
    }

    @Transactional
    public CommandResponse createWallet(WalletRequest request) {
        if(walletRepository.existsByBankAccountNumber(request.getBankAccountNumber())) {
            throw new RuntimeException();
        }
        if(walletRepository.existsByUserIdAndName(request.getUserId(), request.getName())) {
            throw new RuntimeException();
        }

        final Wallet wallet = walletRequestMapper.toEntity(request);
        walletRepository.save(wallet);

        transactionService.create(walletTransactionRequestMapper.toTransactionDto(request));

        return CommandResponse.builder()
                .id(wallet.getId())
                .build();
    }

    @Transactional
    public CommandResponse transferFunds(TransactionRequest request) {
        final Wallet toWallet = getByBankAccountNumber(request.getToWalletBankAccountNumber());
        final Wallet fromWallet = getByBankAccountNumber(request.getFromWalletBankAccountNumber());

        if(fromWallet.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException();
        }

        fromWallet.setBalance(fromWallet.getBalance().subtract(request.getAmount()));
        toWallet.setBalance(toWallet.getBalance().add(request.getAmount()));

        walletRepository.save(toWallet);

        final CommandResponse response = transactionService.create(request);

        return CommandResponse.builder()
                .id(response.getId())
                .build();
    }

    @Transactional
    public CommandResponse addFunds(TransactionRequest request) {
        final Wallet toWallet = getByBankAccountNumber(request.getToWalletBankAccountNumber());

        toWallet.setBalance(toWallet.getBalance().add(request.getAmount()));

        walletRepository.save(toWallet);

        final CommandResponse response = transactionService.create(request);
        return CommandResponse.builder()
                .id(response.getId())
                .build();
    }

    @Transactional
    public CommandResponse withdrawFunds(TransactionRequest request) {
        final Wallet fromWallet = getByBankAccountNumber(request.getFromWalletBankAccountNumber());

        if(fromWallet.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException();
        }

        fromWallet.setBalance(fromWallet.getBalance().subtract(request.getAmount()));

        walletRepository.save(fromWallet);

        final CommandResponse response = transactionService.create(request);

        return CommandResponse.builder()
                .id(response.getId())
                .build();
    }

    public CommandResponse update(Long id, WalletRequest request) {
        final  Wallet requestWallet = walletRepository.findById(id).orElseThrow(() -> new RuntimeException());

        if(!request.getBankAccountNumber().equalsIgnoreCase(requestWallet.getBankAccountNumber()) &&
            walletRepository.existsByUserIdAndName(request.getUserId(), request.getName())) {
            throw new RuntimeException();
        }

        final Wallet wallet = walletRequestMapper.toEntity(request);
        walletRepository.save(wallet);
        return CommandResponse.builder()
                .id(id)
                .build();
    }

    public void deleteById(Long id) {
        final Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        walletRepository.delete(wallet);
    }



}

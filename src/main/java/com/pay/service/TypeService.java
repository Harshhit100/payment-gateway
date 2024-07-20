package com.pay.service;

import com.pay.model.Type;
import com.pay.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeService {

    private final TypeRepository typeRepository;

    public Type getReferenceById(Long id) {
        return typeRepository.getById(id);
    }
}

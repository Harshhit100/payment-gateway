package com.pay.service;

import com.pay.model.User;
import com.pay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getReferenceById(Long id) {
        return userRepository.getReferenceById(id);
    }

}

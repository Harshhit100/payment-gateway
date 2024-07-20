package com.pay.service;

import com.pay.model.Role;
import com.pay.model.RoleType;
import com.pay.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getReferenceByType(Set<RoleType> types) {
        return roleRepository.getReferenceByType(types);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }


}

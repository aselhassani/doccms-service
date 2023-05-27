package com.doccms.domain.service;


import com.doccms.domain.model.Schema;
import com.doccms.domain.model.User;
import com.doccms.port.repository.SchemaRepository;
import com.doccms.port.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    User createUser(User user){
        return userRepository.createUser(user);
    }
    User activateRegistration(String key){
        return userRepository.activateRegistration(key);
    }
    boolean isUserAdmin(){
        return userRepository.isUserAdmin();
    }
}

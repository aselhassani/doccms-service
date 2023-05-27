package com.doccms.port.repository;

import com.doccms.domain.model.Schema;
import com.doccms.domain.model.User;

import java.util.Optional;

public interface UserRepository {

    User createUser(User user);
    User activateRegistration(String key);
    boolean isUserAdmin();

}

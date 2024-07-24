package com.comrade.service;

import com.comrade.entity.UserEntity;
import com.comrade.model.AuthUser;

import java.util.List;

public interface UserService {

    UserEntity save(UserEntity userEntity);
    List<UserEntity> all();
    List<AuthUser> authUsers();
    UserEntity findByEmail(String email);

}

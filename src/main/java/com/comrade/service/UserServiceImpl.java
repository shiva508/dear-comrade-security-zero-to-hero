package com.comrade.service;

import com.comrade.entity.UserEntity;
import com.comrade.model.AuthUser;
import com.comrade.model.exception.RecordNotFoundException;
import com.comrade.repository.UserRepository;
import com.comrade.util.DhaConstants;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public UserEntity save(UserEntity userEntity) {
        String encodedPassword = bCryptPasswordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodedPassword);
        return userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public List<UserEntity> all() {
        return userRepository.findAllUsers();
    }

    @Override
    @Transactional
    public List<AuthUser> authUsers() {
        List<UserEntity> users = userRepository.findAllUsers();
        return users.stream().map(AuthUser::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
                             .orElseThrow(()->new RecordNotFoundException(String.format(DhaConstants.RECORD_NOT_FOUND, email)));
    }
}

package com.comrade.service;


import com.comrade.entity.UserEntity;
import com.comrade.model.AuthUser;
import com.comrade.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        log.info("UserDetailsServiceImpl username= {}",username);
        UserEntity userEntity = userRepository.findByEmail(username)
                                              .orElseThrow(()->new UsernameNotFoundException(" No user found"));
        log.info("Output {}",userEntity);
        return new AuthUser(userEntity);
    }
}

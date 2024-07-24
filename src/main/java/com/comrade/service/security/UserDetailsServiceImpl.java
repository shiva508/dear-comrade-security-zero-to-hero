package com.comrade.service.security;

import com.comrade.entity.UserEntity;
import com.comrade.model.AuthUser;
import com.comrade.model.exception.RecordNotFoundException;
import com.comrade.repository.UserRepository;
import com.comrade.util.DhaConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
@Slf4j
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserDetailsServiceImpl:: loadUserByUsername  username {} ",username);
       UserEntity userEntity = userRepository.findByEmail(username)
                                             .orElseThrow(()->new RecordNotFoundException(String.format(DhaConstants.RECORD_NOT_FOUND, username)));
        log.info("UserDetailsServiceImpl:: loadUserByUsername  completed");
        return new AuthUser(userEntity);
    }
}

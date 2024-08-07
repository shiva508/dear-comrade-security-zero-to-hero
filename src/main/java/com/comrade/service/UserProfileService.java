package com.comrade.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comrade.model.SecurityUserProfile;
import com.comrade.repository.UserProfileRepository;

@Service
@Transactional
public class UserProfileService implements UserDetailsService {
	
	private final UserProfileRepository userProfileRepository;
	
	public UserProfileService(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		return userProfileRepository.findByUsername(username)
							 .map(SecurityUserProfile::new)
							 .orElseThrow(()->new UsernameNotFoundException("User Not Found :"+username));
	}

}

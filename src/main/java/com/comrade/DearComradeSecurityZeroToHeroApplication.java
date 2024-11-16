package com.comrade;

import com.comrade.entity.UserProfile;
import com.comrade.repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class DearComradeSecurityZeroToHeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(DearComradeSecurityZeroToHeroApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(UserProfileRepository userProfileRepository){
		return args -> {
			UserProfile userProfile =  UserProfile.builder()
												  .username("shiva")
												  .password("shiva")
												  .roles("ROLE_USER,ROLE_ADMIN")
												  .build();
			UserProfile save = userProfileRepository.save(userProfile);
			log.info("applicationRunner:: {}",save);
		};
	}

}

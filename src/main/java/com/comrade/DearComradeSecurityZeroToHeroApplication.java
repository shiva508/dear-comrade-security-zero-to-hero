package com.comrade;

import com.comrade.entity.UserProfile;
import com.comrade.repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
			UserProfile userProfile_1 =  UserProfile.builder()
					.username("dasari")
					.password("dasari")
					.roles("ROLE_USER")
					.build();
			List<UserProfile> save = userProfileRepository.saveAll(List.of(userProfile,userProfile_1));
			log.info("applicationRunner:: {}",save);
		};
	}

}

package com.nickleback.boilerplate;

import com.nickleback.boilerplate.domain.Role;
import com.nickleback.boilerplate.domain.User;
import com.nickleback.boilerplate.repository.RoleRepository;
import com.nickleback.boilerplate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RequiredArgsConstructor
public class BoilerplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoilerplateApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

}

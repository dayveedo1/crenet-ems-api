package com.david.EmployeeMSAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EmployeeMsapiApplication extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EmployeeMsapiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeMsapiApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner run(AppService service){
//		return args -> {
//
//			service.saveRole(new Role(null, "ROLE_ADMIN"));
//			service.saveRole(new Role(null, "ROLE_USER"));
//
////			service.saveUser(new AppUser(null, "cole47@gmail.com", "Cole", "Amadi", "cole47@gmail.com", "1234", "male", "27", new Date(), new ArrayList<>()));
////
////			service.AddRoleToUser("cole47@gmail.com", "ROLE_ADMIN");
////			service.AddRoleToUser("cole47@gmail.com", "ROLE_USER");
//		};
//	}


}

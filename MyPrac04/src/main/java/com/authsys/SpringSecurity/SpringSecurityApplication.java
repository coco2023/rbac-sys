package com.authsys.SpringSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableFeignClients
@EnableWebMvc

//public class SpringSecurityApplication implements CommandLineRunner {
	public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

//	@Autowired
//	private RoleRepository roleRepository;
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private UserRoleRepository userRoleRepository;

//	@Override
//	public void run(java.lang.String... args) {
//		//初始化角色信息
//		for (RoleType roleType : RoleType.values()) {
//			roleRepository.save(new Role(roleType.getName(), roleType.getDescription()));
//		}
//		//初始化一个 admin 用户
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		User user = User.builder().enabled(true).fullName("admin").userName("root").password(bCryptPasswordEncoder.encode("root")).build();
//		userRepository.save(user);
//		Role role = roleRepository.findByName(RoleType.ADMIN.getName()).get();
//		userRoleRepository.save(new UserRole(user, role));
//	}

}

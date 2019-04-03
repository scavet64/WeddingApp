package com.scavettapps.wedding;

import java.util.Arrays;
import java.util.Collection;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.scavettapps.wedding.domain.Role;
import com.scavettapps.wedding.domain.WeddingUser;
import com.scavettapps.wedding.repository.RoleRepository;
import com.scavettapps.wedding.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserRepository users;

	@Autowired
	RoleRepository roles;

//	@Autowired
//	PrivilegeRepository privileges;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		LOGGER.info("Init test data");
		// This is really just test code
		// TODO: delete this when we are ready
		if (users.findByUsername("admin").isEmpty()) {
			Role adminRole = roles.findByName("ROLE_ADMIN");
			WeddingUser user = new WeddingUser();
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("password"));
			user.setEmail("email@email.com");
			user.setFirstname("firstname");
			user.setLastname("lastname");
			user.setEnabled(true);
			user.setRoles(Arrays.asList(adminRole));
			users.save(user);
		}
		if (users.findByUsername("user").isEmpty()) {
			Role userRole = roles.findByName("ROLE_USER");
			WeddingUser user = new WeddingUser();
			user.setUsername("user");
			user.setPassword(passwordEncoder.encode("password"));
			user.setEmail("email@email.com");
			user.setFirstname("firstname");
			user.setLastname("lastname");
			user.setEnabled(true);
			user.setRoles(Arrays.asList(userRole));
			users.save(user);
		}
		LOGGER.info("finished test data");
	}
//
//	@Transactional
//	private Privilege createPrivilegeIfNotFound(String name) {
//
//		Privilege privilege = privileges.findByName(name);
//		if (privilege == null) {
//			privilege = new Privilege(name);
//			privileges.save(privilege);
//		}
//		return privilege;
//	}
//
//	@Transactional
//	private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
//
//		Role role = roles.findByName(name);
//		if (role == null) {
//			role = new Role(name);
//			role.setPrivileges(privileges);
//			roles.save(role);
//		}
//		return role;
//	}
}
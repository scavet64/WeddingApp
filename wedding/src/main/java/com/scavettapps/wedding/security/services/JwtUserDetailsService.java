package com.scavettapps.wedding.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scavettapps.wedding.domain.WeddingUser;
import com.scavettapps.wedding.repository.UserRepository;
import com.scavettapps.wedding.security.jwt.JwtUserFactory;

@Service("JwtUserDetailsService")
@Qualifier("jwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUserFactory jwtFactory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		WeddingUser user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException(String.format("No user found with username '%s'.", username)));

		return jwtFactory.create(user);
	}
}
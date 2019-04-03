package com.scavettapps.wedding.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.scavettapps.wedding.domain.WeddingUser;
import com.scavettapps.wedding.repository.UserRepository;


@Service
public class UserDetailsMapperService {

    @Autowired
    private UserRepository userRepository;

    public WeddingUser getLeashUserFromUserDetails(UserDetails userDetails) throws UserNotFoundException {
    	WeddingUser user = userRepository.findByUsername(userDetails.getUsername())
		.orElseThrow(() -> new UserNotFoundException(userDetails.getUsername()));

	return user;
    }

}

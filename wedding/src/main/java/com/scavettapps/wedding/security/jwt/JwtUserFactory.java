package com.scavettapps.wedding.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scavettapps.wedding.domain.WeddingUser;
import com.scavettapps.wedding.security.services.RolesToAuthoritiesService;


@Service
public final class JwtUserFactory {

   @Autowired
   private RolesToAuthoritiesService rolesToAuthService;
   
   public JwtUserFactory() {
   }

   public JwtUser create(WeddingUser user) {
	   if(user == null) {
		   throw new IllegalArgumentException("User was null");
	   }
      return new JwtUser(
            user.getId(),
            user.getUsername(),
            user.getFirstname(),
            user.getLastname(),
            user.getEmail(),
            user.getPassword(),
            rolesToAuthService.getAuthorities(user.getRoles()),
            user.isEnabled(),
            user.getLastPasswordResetDate());
   }
}

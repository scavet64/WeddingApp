package com.scavettapps.wedding.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.scavettapps.wedding.domain.Privilege;
import com.scavettapps.wedding.domain.Role;


@Service
public class RolesToAuthoritiesService {

    public Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
	return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {

	List<String> privileges = new ArrayList<>();
	List<Privilege> collection = new ArrayList<>();
	for (Role role : roles) {
	    privileges.add(role.getName());
	    collection.addAll(role.getPrivileges());
	}
	for (Privilege item : collection) {
	    privileges.add(item.getName());
	}
	return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
	List<GrantedAuthority> authorities = new ArrayList<>();
	for (String privilege : privileges) {
	    authorities.add(new SimpleGrantedAuthority(privilege));
	}
	return authorities;
    }
}

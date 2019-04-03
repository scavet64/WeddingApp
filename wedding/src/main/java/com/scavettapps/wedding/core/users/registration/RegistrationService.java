package com.scavettapps.wedding.core.users.registration;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scavettapps.wedding.domain.Role;
import com.scavettapps.wedding.domain.WeddingUser;
import com.scavettapps.wedding.repository.RoleRepository;
import com.scavettapps.wedding.repository.UserRepository;
import com.scavettapps.wedding.security.controller.AuthenticationException;
import com.scavettapps.wedding.security.jwt.JsonWebToken;
import com.scavettapps.wedding.security.services.AuthenticationService;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 
     * @param req The data object containing information from the user
     * @return response for the new registration request
     * 
     * @throws {@link DataException} if there is a problem accessing the database
     * @throws {@link AuthenticationException} if there is a problem authenticating
     *         the new user
     * @throws {@link UserAlreadyExistsException} if the user already exists and
     *         will not be created again
     **/
    @Transactional
    public RegistrationResponse registerNewUser(RegistrationRequest req) {
        // Create new user object which will validate the parameters
        try {
            // TODO: Find a more efficient way to do this. Users are always going to be this
            // role at least for now.
            Role userRole = roleRepo.findByName("ROLE_USER");
            List<Role> roles = Collections.singletonList(userRole);

            WeddingUser newUser = new WeddingUser(req.getUsername(), passwordEncoder.encode(req.getPassword()),
                    req.getFirstname(), req.getLastname(), req.getEmail(), roles);

            // Check to see if this user exists before we try and save a new one
            if (userRepo.findByUsername(req.getUsername()).isPresent()) {
                throw new UserAlreadyExistsException("Username already exists: " + req.getUsername());
            }

            // TODO: Add password validation to ensure strong enough

            // Save the new user
            userRepo.save(newUser);

            // Get the token for the new user and return the response
            JsonWebToken token = authService.getTokenForUser(req.getUsername(), req.getPassword());
            return new RegistrationResponse(newUser, token);
        } catch (DataException ex) {
            throw new RegistrationException("There was an issue with the database", ex);
        } catch (AuthenticationException ex) {
            throw new RegistrationException("Could not authenticate the new user", ex);
        }
    }

}

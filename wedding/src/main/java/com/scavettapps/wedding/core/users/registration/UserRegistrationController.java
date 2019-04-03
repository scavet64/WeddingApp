package com.scavettapps.wedding.core.users.registration;

import static org.springframework.http.ResponseEntity.ok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scavettapps.wedding.core.response.DataResponse;
import com.scavettapps.wedding.core.response.ErrorResponse;
import com.scavettapps.wedding.core.response.Response;

@RestController
@RequestMapping("/registration")
public class UserRegistrationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<Response> registerUser(@RequestBody RegistrationRequest regReq) {
        try {
            RegistrationResponse newUserResponse = registrationService.registerNewUser(regReq);
            return ok(new DataResponse(newUserResponse));
        } catch (RegistrationException ex) {
            ex.printStackTrace();
            logger.info(ex.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(ex));
        } catch (UserAlreadyExistsException ex) {
            logger.debug("Could not register user", ex);
            logger.info(ex.toString());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("Username already exists"));
        }
    }

}

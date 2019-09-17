package com.sap.pfs.oauth.rest;

import com.sap.pfs.oauth.auth.LoginAttemptService;
import com.sap.pfs.oauth.auth.User;
import com.sap.pfs.oauth.auth.UserRepository;
import com.sap.pfs.oauth.resource.SignupResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Slf4j
@ApiController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final LoginAttemptService loginAttemptService;

    @PutMapping("/password/modify")
    public ResponseEntity changePassword(@RequestBody SignupResource resource, Principal principal) {
        // User user = userRepository.findByEmail(principal.getName());
        User user = userRepository.findByEmail(resource.getEmail());
        user.modifyPassword(resource.getPassword());
        user = userRepository.save(user);
        log.info("Password changed for {}",user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/password/reset")
    public ResponseEntity resetPassword(@RequestBody SignupResource resource) {
        User user = userRepository.findByEmail(resource.getEmail());
        user.modifyPassword(resource.getPassword());
        user = userRepository.save(user);

        // In case the user is blocked for wrong entry of passwords, reset the attemptsCache
        loginAttemptService.invalidateAttemptsCache(resource.getEmail());

        log.info("Password changed for {}",user);
        return ResponseEntity.ok().build();
    }
}
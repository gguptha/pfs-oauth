package com.sap.pfs.oauth.rest;

import com.sap.pfs.oauth.auth.*;
import com.sap.pfs.oauth.engine.UserSignedUpEvent;
import com.sap.pfs.oauth.resource.SignupResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

@Slf4j
@ApiController
@RequiredArgsConstructor
public class SignupController {

    private final UserRepository userRepository;

    private final PasswordHistoryRepository passwordHistoryRepository;

    private final ApplicationEventPublisher publisher;

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@RequestBody SignupResource signupResource){
        String activationCode = UUID.randomUUID().toString();
        User user = new User(signupResource.getEmail(),signupResource.getPassword(),new HashSet<>(Arrays.asList(Roles.MAIN_LOAN_PARTNER)),activationCode,true);
        user = userRepository.save(user);
        log.info("{} created",user);
        publisher.publishEvent(new UserSignedUpEvent(user.getEmail(), user.getActivation()));

        PasswordHistory passwordHistory = new PasswordHistory(user.getEmail(), user.getPassword());
        passwordHistory = passwordHistoryRepository.save(passwordHistory);

        return ResponseEntity.ok(true);
    }

    @PostMapping("/admin/signup")
    public ResponseEntity<Boolean> adminSignUp(@RequestBody SignupResource signupResource)
    {
        User user = new User(signupResource.getEmail(), signupResource.getPassword(), new HashSet<>(Arrays.asList(Roles.APPRAISAL_OFFICER)),
                "",true);
        user = userRepository.save(user);
        log.info("{} created", user);
        // publisher.publishEvent(new UserSignedUpEvent(user.getEmail(), user.getActivation()));

        PasswordHistory passwordHistory = new PasswordHistory(user.getEmail(), user.getPassword());
        passwordHistory = passwordHistoryRepository.save(passwordHistory);

        return ResponseEntity.ok(true);
    }

    @PutMapping("/signup/verify/{activation}")
    public ResponseEntity verify(@PathVariable("activation") String activation){
        User user = userRepository.findByActivation(activation);
        user.activate();
        user = userRepository.save(user);
        log.info("{} Activated",user);
        return ResponseEntity.ok().build();
    }
}

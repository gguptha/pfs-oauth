package com.sap.pfs.oauth.auth;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Slf4j
@Service("systemDataLoader")
@AllArgsConstructor
public class SystemUserLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... strings) {
        if(userRepository.findByEmail("admin") == null)
            userRepository.save(new User("admin","admin", Roles.PFS_IT_TEAM));
    }
}

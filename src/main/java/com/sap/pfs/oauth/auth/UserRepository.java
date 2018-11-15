package com.sap.pfs.oauth.auth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String > {
    User findByEmail(String username);
    User findByActivation(String activation);
}

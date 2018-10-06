package com.sap.pfs.oauth.pfsoauth.auth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String > {
    User findByEmail(String username);
}

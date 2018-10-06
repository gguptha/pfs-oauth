package com.sap.pfs.oauth.pfsoauth.auth;

import com.sap.pfs.oauth.pfsoauth.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : fahadfazil
 * @since : 22/12/17
 */


@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserDetailsManager {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

        if (user == null)
            throw new CustomException("User not found", HttpStatus.UNAUTHORIZED);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.addAll(user.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.abbreviation)).collect(Collectors.toSet()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

    @Override
    public void createUser(UserDetails userDetails) {

    }

    @Override
    public void updateUser(UserDetails userDetails) {

    }

    @Override
    public void deleteUser(String s) {

    }

    @Override
    public void changePassword(String s, String s1) {

    }

    @Override
    public boolean userExists(String s) {
        return false;
    }
}
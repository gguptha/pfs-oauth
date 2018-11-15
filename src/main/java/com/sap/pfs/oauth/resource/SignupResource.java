package com.sap.pfs.oauth.resource;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupResource {

    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String password;

    public SignupResource(String firstName, String lastName, String email, String mobile, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }
}

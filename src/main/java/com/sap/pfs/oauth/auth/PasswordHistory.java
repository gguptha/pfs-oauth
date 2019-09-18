package com.sap.pfs.oauth.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.pfs.oauth.util.AuditedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PasswordHistory extends AuditedEntity {

    private String email;

    @JsonIgnore
    private String password;

    public PasswordHistory(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
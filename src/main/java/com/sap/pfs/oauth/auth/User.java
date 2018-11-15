package com.sap.pfs.oauth.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.pfs.oauth.util.AuditedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class User extends AuditedEntity {

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Roles> authorities = new HashSet<>(0);

    @Setter
    private String activation;

    @Setter
    private boolean active;

    public User(String email, String password, Roles authority) {
        this.email = email;
        this.password = password;
        this.authorities.add(authority);
        this.active = true;
    }

    public User(String email, String password, Set<Roles> authorities, String activation, boolean active) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.activation = activation;
        this.active = active;
    }

    public User activate(){
        this.active = true;
        this.activation = "";
        return this;
    }
}

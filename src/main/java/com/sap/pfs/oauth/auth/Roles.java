package com.sap.pfs.oauth.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  Roles {
    ADMIN("ADMIN","Admin"),
    USER("USER","User"),
    MANAGER("MANAGER","Manager");

    String abbreviation;
    String description;

    Roles(String abbreviation,String description){
        this.abbreviation = abbreviation;
        this.description = description;
    }
}

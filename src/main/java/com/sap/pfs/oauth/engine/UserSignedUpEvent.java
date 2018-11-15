package com.sap.pfs.oauth.engine;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSignedUpEvent {
    private String email;
    private String activationCode;
}

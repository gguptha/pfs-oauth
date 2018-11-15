package com.sap.pfs.oauth.engine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignupEmailEngine {


    @EventListener
    public void sendSingupEmail(UserSignedUpEvent event){

    }
}

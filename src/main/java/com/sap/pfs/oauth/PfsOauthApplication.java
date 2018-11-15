package com.sap.pfs.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@SpringBootApplication
public class PfsOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfsOauthApplication.class, args);
	}

	@GetMapping("time")
	public ResponseEntity getTime(){
		return ResponseEntity.ok(LocalDateTime.now());
	}
}

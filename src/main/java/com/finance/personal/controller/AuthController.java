package com.finance.personal.controller;

import com.finance.personal.dto.request.LoginDTORequest;
import com.finance.personal.model.UserEntity;
import com.finance.personal.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody LoginDTORequest loginDTORequest) {
        try {
            UsernamePasswordAuthenticationToken userPassword = new UsernamePasswordAuthenticationToken(loginDTORequest.getEmail(), loginDTORequest.getPassword());

            Authentication authentication = authenticationManager.authenticate(userPassword);

            String token = tokenService.generateToken((UserEntity) authentication.getPrincipal());

            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        catch (SecurityException exception) {
            throw new SecurityException("Unauthorized access!", exception);
        }
    }
}

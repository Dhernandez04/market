package com.dhernandez.market.web.controller;

import com.dhernandez.market.domain.dto.AuthenticationRequest;
import com.dhernandez.market.domain.dto.AuthenticationResponse;
import com.dhernandez.market.domain.service.UserDetailServiceImplement;
import com.dhernandez.market.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserDetailServiceImplement userDetailServiceImplement;

    private JWTUtil jwtUtil;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager,UserDetailServiceImplement userDetailServiceImplement, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailServiceImplement =userDetailServiceImplement;
        this.jwtUtil= jwtUtil;
    }



    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = userDetailServiceImplement.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(jwt),HttpStatus.OK);
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}

package com.example.ss3.controller.api;

import com.example.ss3.entity.UserEntity;
import com.example.ss3.model.AuthenticationRequest;
import com.example.ss3.model.AuthenticationResponse;
import com.example.ss3.service.CustomService;
import com.example.ss3.service.UserCustomService;
import com.example.ss3.service.UserService;
import com.example.ss3.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthAPIController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserCustomService userCustomService;
    @Autowired
    private CustomService customService;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest) throws  Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername()
                            , authenticationRequest.getPassword()));
        } catch (BadCredentialsException e){
            throw  new Exception("Incorrect username and/or password",e);
        }
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        final  String jwt = jwtUtil.generateToken(userDetails);
        UserEntity userEntity = userCustomService.getUserByName(userDetails.getUsername());
        return  ResponseEntity.ok(new AuthenticationResponse(jwt,userEntity.getUsername(),userEntity.getEmail()));
    }
}

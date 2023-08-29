package com.VirtualCard.maplerads.Controller;

import com.VirtualCard.maplerads.Exception.EmailExistsException;
import com.VirtualCard.maplerads.Exception.EmailNotFound;
import com.VirtualCard.maplerads.Service.AuthenticationService;
import com.VirtualCard.maplerads.advice.CustomExceptionHandler;
import com.VirtualCard.maplerads.dao.request.CustomerRequest;
import com.VirtualCard.maplerads.dao.request.SignInRequest;
import com.VirtualCard.maplerads.dao.response.AuthenticationResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Authentication {

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody CustomerRequest customerRequest)
            throws CustomExceptionHandler, IOException, EmailExistsException, InterruptedException {

        if (customerRequest == null && customerRequest.toString().equals("")){

            throw new RuntimeException("Add values");
        }

        authenticationService.register(customerRequest);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody SignInRequest SignInRequest) throws EmailNotFound {
        return ResponseEntity.ok(authenticationService.signIn(SignInRequest));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() throws EmailNotFound {
        return ResponseEntity.ok("HELLOOOOO");
    }
}

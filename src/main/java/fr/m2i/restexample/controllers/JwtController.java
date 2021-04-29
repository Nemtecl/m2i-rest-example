package fr.m2i.restexample.controllers;

import fr.m2i.restexample.dtos.JwtRequest;
import fr.m2i.restexample.dtos.JwtResponse;
import fr.m2i.restexample.dtos.RegisterDto;
import fr.m2i.restexample.dtos.UserDto;
import fr.m2i.restexample.exceptions.M2I400Exception;
import fr.m2i.restexample.security.jwt.JwtUtil;
import fr.m2i.restexample.services.UserService;
import fr.m2i.restexample.services.impl.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO role based https://www.devglan.com/spring-security/jwt-role-based-authorization


@RequestMapping("/api")
@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest authenticationRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));

        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtUtil.generateToken(userDetails);

        return new JwtResponse(token);
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterDto registerDto) throws M2I400Exception {
        return userService.create(registerDto);
    }
}

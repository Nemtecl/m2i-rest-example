package fr.m2i.restexample.controllers;

import fr.m2i.restexample.repositories.entities.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UserDetails admin(@AuthenticationPrincipal UserDetails user) {

        return user;
    }

    @GetMapping("/any")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public UserDetails adminOrUser(@AuthenticationPrincipal UserDetails user) {

        return user;
    }
}

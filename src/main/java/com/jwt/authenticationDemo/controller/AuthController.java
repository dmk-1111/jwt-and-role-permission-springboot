package com.jwt.authenticationDemo.controller;

import com.jwt.authenticationDemo.jwt.JwtUtil;
import com.jwt.authenticationDemo.model.Role;
import com.jwt.authenticationDemo.model.UserInfo;
import com.jwt.authenticationDemo.repository.RoleRepository;
import com.jwt.authenticationDemo.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserInfoRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtil jwtUtils;
    @PostMapping("/signin")
    public String authenticateUser(@RequestBody UserInfo user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername());
    }
    @PostMapping("/signup")
    public String registerUser(@RequestBody UserInfo user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Error: Username is already taken!";
        }
        // Create new user's account
        UserInfo newUser = new UserInfo(
                null,
                user.getUsername(),
                encoder.encode(user.getPassword()),
                true,
                null
        );
        userRepository.save(newUser);
        return "User registered successfully!";
    }

    @PostMapping("/role")
    public String registerRole(@RequestBody Role role) {
        if (roleRepository.existsByRole(role.getRole())) {
            return "Error: Role is already taken!";
        }
        // Create new user's account
        Role newRole = new Role(
                null,
                role.getRole(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        roleRepository.save(newRole);
        return "Role registered successfully!";
    }
}
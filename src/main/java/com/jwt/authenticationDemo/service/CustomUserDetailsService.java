package com.jwt.authenticationDemo.service;

import com.jwt.authenticationDemo.model.Role;
import com.jwt.authenticationDemo.model.UserInfo;
import com.jwt.authenticationDemo.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userInfoRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getRoles(user.getRoles())
        );
    }

    public Collection<? extends GrantedAuthority> getRoles(Collection<Role> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole()))
               .collect(Collectors.toList());
    }
}
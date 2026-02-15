package com.jwt.authenticationDemo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean enabled;

    // fetch = FetchType.EAGER This tells JPA: "Whenever I load a User, immediately load all roles too."
    // cascade = CascadeType.ALL : This means: Any operation done to User will also be done to Role.
    //Examples:
    //Save user → roles also saved
    //Delete user → roles also deleted ❗
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;
}
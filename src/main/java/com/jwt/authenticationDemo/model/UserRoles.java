package com.jwt.authenticationDemo.model;


import com.jwt.authenticationDemo.dto.request.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_roles")
public class UserRoles {
    @EmbeddedId
    private UserRole id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserInfo user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;
}

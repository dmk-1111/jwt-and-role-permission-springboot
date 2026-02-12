package com.jwt.authenticationDemo.dto.request;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserRole implements Serializable {
    private Long userId;
    private Long roleId;
}

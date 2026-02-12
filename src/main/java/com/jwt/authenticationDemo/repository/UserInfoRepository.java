package com.jwt.authenticationDemo.repository;

import com.jwt.authenticationDemo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
    boolean existsByUsername(String username);
}

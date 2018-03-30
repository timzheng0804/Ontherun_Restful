package com.ontherun.restful_api.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findById(Long id);
    UserInfo findByPhone(String phone);
    UserInfo findByUsername(String username);
    UserInfo findByPhoneAndPassword(String phone, String password);
}

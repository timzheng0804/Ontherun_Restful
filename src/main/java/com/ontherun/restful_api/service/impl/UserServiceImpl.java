package com.ontherun.restful_api.service.impl;

import com.ontherun.restful_api.domain.UserInfo;
import com.ontherun.restful_api.domain.UserRepository;
import com.ontherun.restful_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<UserInfo> findAllUsers() {
        List<UserInfo> userInfos = new ArrayList<>();
        userInfos.addAll(userRepository.findAll());
        return userInfos;
    }

    @Override
    public UserInfo findUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void addUser(UserInfo user) { userRepository.save(user); }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    @Override
    public UserInfo findUserByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public UserInfo findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Long authenticateUser(String phone, String password) {
        UserInfo userInfo = userRepository.findByPhoneAndPassword(phone, password);
        if (userInfo == null) throw new BadCredentialsException("用户手机号或者密码错误!");
        return userInfo.getId();
    }
}

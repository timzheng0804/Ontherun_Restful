package com.ontherun.restful_api.service;

import com.ontherun.restful_api.domain.UserInfo;
import java.util.List;

public interface UserService {

    /**
     * Return all users in list
     * @return List<UserInfo>
     */
    List<UserInfo> findAllUsers();

    /**
     * Get user by id
     * @param id
     * @return UserInfo
     */
    UserInfo findUserById(Long id);

    /**
     * Add user to database
     * @param user
     */
    void addUser(UserInfo user);

    /**
     * delete user in database
     * @param id
     */
    void deleteUser(Long id);

    /**
     * find user by user's phone number
     * @param phone
     * @return UserInfo
     */
    UserInfo findUserByPhone(String phone);

    /**
     * find user by username
     * @param username
     * @return UserInfo
     */
    UserInfo findUserByUsername(String username);

    /**
     * Authenticate user, return userId if success
     * @param phone user phone number
     * @param password user password
     * @return userId
     */
    Long authenticateUser(String phone, String password);
}

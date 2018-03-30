package com.ontherun.restful_api.controller;

import com.ontherun.restful_api.domain.UserInfo;
import com.ontherun.restful_api.exception.AlreadyExistException;
import com.ontherun.restful_api.exception.UserNotFoundException;
import com.ontherun.restful_api.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    /**
     * Get All users from the database
     * @return List<UserInfo>
     */
    @RequestMapping()
    public ResponseEntity<List<UserInfo>> getAllUsers() {
        List<UserInfo> userList = userService.findAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    /**
     * Get User by userId
     * @param id User Id
     * @return UserInfo / User Not Found
     */
    @RequestMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        UserInfo user = userService.findUserById(id);
        if (user == null) {                                                 // User Not Found, Throw Exception
            throw new UserNotFoundException(id);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Add user to database
     * @param userInfo user's Info
     * @return User Creation success / Already Exist Exception
     */

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody UserInfo userInfo) {
        String phone = userInfo.getPhone();
        String userName = userInfo.getUsername();
        if (userService.findUserByPhone(phone) != null) {                   // Check if phone number already existed
            throw new AlreadyExistException("Phone number : " + phone);
        } else if (userService .findUserByUsername(userName) != null) {     // Check if user name already existed
            throw new AlreadyExistException("Username : " + userName);
        }
        userService.addUser(userInfo);                                      // Add user to database
        return new ResponseEntity<>("User Successfully Created", HttpStatus.CREATED);
    }

    /**
     * Delete user in database
     * @param id User Id
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}

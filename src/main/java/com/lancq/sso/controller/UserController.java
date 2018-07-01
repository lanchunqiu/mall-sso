package com.lancq.sso.controller;

import com.lancq.user.IUserCoreService;
import com.lancq.user.dto.UserLoginRequest;
import com.lancq.user.dto.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lancq
 * @Description
 * @Date 2018/7/1
 **/
@RestController
public class UserController {
    @Autowired
    private IUserCoreService userCoreService;
    @PostMapping("/login")
    public ResponseEntity doLogin(String username, String password){
        UserLoginRequest request = new UserLoginRequest();
        request.setUserName(username);
        request.setPassword(password);
        UserLoginResponse response = userCoreService.login(request);
        return ResponseEntity.ok(response);
    }

}
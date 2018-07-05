package com.lancq.sso.controller;

import com.lancq.sso.controller.support.ResponseData;
import com.lancq.user.IUserCoreService;
import com.lancq.user.dto.UserLoginRequest;
import com.lancq.user.dto.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author lancq
 * @Description
 * @Date 2018/7/1
 **/
@RestController
public class UserController extends BaseController{
    @Autowired
    private IUserCoreService userCoreService;

    @PostMapping("/login")
    public ResponseEntity doLogin(String username, String password, HttpServletResponse response){
        UserLoginRequest request = new UserLoginRequest();
        request.setUserName(username);
        request.setPassword(password);
        UserLoginResponse userLoginResponse = userCoreService.login(request);
        response.addHeader("Set-cookie","access_token" + userLoginResponse.getToken() + ";Path=/;HttpOnly");
        return ResponseEntity.ok(userLoginResponse);
    }

}

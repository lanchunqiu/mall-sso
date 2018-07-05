package com.lancq.sso.controller;

import com.lancq.sso.Anoymous;
import com.lancq.sso.controller.support.ResponseData;
import com.lancq.user.IUserCoreService;
import com.lancq.user.dto.UserLoginRequest;
import com.lancq.user.dto.UserLoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    Logger Log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IUserCoreService userCoreService;

    @Anoymous
    @PostMapping("/login")
    public UserLoginResponse doLogin(String username, String password, HttpServletResponse response){
        UserLoginRequest request = new UserLoginRequest();
        request.setUserName(username);
        request.setPassword(password);
        UserLoginResponse userLoginResponse = userCoreService.login(request);
        Log.debug("userLoginResponse:" + userLoginResponse);
        response.addHeader("Set-Cookie","access_token=" + userLoginResponse.getToken() + ";Path=/;HttpOnly");
        return userLoginResponse;
    }

    @GetMapping("/test")
    public String test(){
        return "success:"+getUid();
    }

}

package com.lancq.sso.controller;

import com.lancq.commom.annotation.Anoymous;
import com.lancq.commom.constants.MallWebConstant;
import com.lancq.sso.controller.support.ResponseData;
import com.lancq.sso.controller.support.ResponseEnum;
import com.lancq.user.IUserCoreService;
import com.lancq.user.dto.UserLoginRequest;
import com.lancq.user.dto.UserLoginResponse;
import com.lancq.user.dto.UserQueryRequest;
import com.lancq.user.dto.UserRegisterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    /**
     * 用户登录
     * @param username
     * @param password
     * @param response
     * @return
     */
    @Anoymous
    @PostMapping("/login")
    public ResponseData doLogin(String username, String password, HttpServletResponse response){
        UserLoginRequest request = new UserLoginRequest();
        request.setUserName(username);
        request.setPassword(password);
        UserLoginResponse userLoginResponse = userCoreService.login(request);
        Log.debug("userLoginResponse:" + userLoginResponse);
        response.addHeader("Set-Cookie","access_token=" + userLoginResponse.getToken() + ";Path=/;HttpOnly");

        ResponseData data=new ResponseData();
        data.setMessage(userLoginResponse.getMsg());
        data.setCode(userLoginResponse.getCode());
        data.setData(MallWebConstant.MALL_ACTIVITY_ACCESS_URL);
        return data;
    }

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @param mobile 手机
     * @return
     */
    @ResponseBody
    @PostMapping("/register")
    public ResponseData register(String username, String password, String mobile){
        ResponseData data=new ResponseData();
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setMobile(mobile);
        userRegisterRequest.setUsername(username);
        userRegisterRequest.setPassword(password);
        try {
            userCoreService.register(userRegisterRequest);
        } catch(Exception e) {
            data.setMessage(ResponseEnum.FAILED.getMsg());
            data.setCode(ResponseEnum.FAILED.getCode());
        }

        return data;
    }

    @GetMapping("/test")
    public String test(){
        return "success:"+getUid();
    }

}

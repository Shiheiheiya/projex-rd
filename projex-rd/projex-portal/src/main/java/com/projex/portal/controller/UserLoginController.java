package com.projex.portal.controller;

import com.projex.portal.service.UserLoginService;
import com.projex.portal.vo.UserResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api(tags = "UserLoginController")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @ApiOperation("测试")
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public UserResult getUserInfo(Integer userId){
        return userLoginService.getUserById(userId);
    }
}

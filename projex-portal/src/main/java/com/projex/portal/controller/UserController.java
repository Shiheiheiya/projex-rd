package com.projex.portal.controller;

import com.projex.portal.api.CommonResult;
import com.projex.portal.service.UserService;
import com.projex.portal.vo.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "UserLoginController")
public class UserController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public CommonResult<String> getUserInfo(HttpServletRequest request,
                                            Integer userId){
        String token = request.getHeader("token");
        return CommonResult.success(token);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return token凭证
     */
    @ResponseBody
    @PostMapping("/login")
    public Object login(@RequestParam String username,
                        @RequestParam String password) {

        String token = userService.login(username, password);
        if (token == null){
            return CommonResult.failed("用户名或密码错误");
        }
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("tokenHead", tokenHead);
        User userInfo = userService.getUserByUsername(username);
        result.put("userId", userInfo.getUserId());
        result.put("userName", userInfo.getUsername());
        result.put("recentCompany", userInfo.getRecentCompany());
        return CommonResult.success(result);
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @PostMapping("/register")
    public CommonResult<String> register(@RequestParam String username,
                                   @RequestParam String password){
        Integer result = userService.register(username, password);
        if (result > 0) {
            return CommonResult.success(null, "注册成功");
        }
        return CommonResult.failed("注册失败");
    }

}

package com.projex.portal.service;

import com.projex.portal.dao.UserDao;
import com.projex.portal.utils.TokenTools;
import com.projex.portal.vo.User;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(Integer userId) {
        return userDao.getUser(userId);
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public Integer updateTokenByUserId(Integer userId, String token) {
        return userDao.updateTokenByUserId(userId, token);
    }

    public Integer register(String username, String password) {
        User userResult = getUserByUsername(username);
        if (userResult != null){
            Assert.fail("用户已存在");
        }
        return userDao.register(username, password);
    }

    public String login(String username, String password) {
        String token = null;
        //获取用户信息
        User userResult = getUserByUsername(username);
        //账号不存在 或 密码错误
        if (userResult == null || !userResult.getPassword().equals(password)) {
            return null;
        } else  {
            //生成token
            token = TokenTools.getToken(userResult.getUserId(), username);
            //保存token到数据库成功
            updateTokenByUserId(userResult.getUserId(), token);
            return token;
        }
    }

    public Boolean updateRecentCompany(Integer userId, Integer companyId){
        Integer result = userDao.updateRecentCompany(userId, companyId);
        return result > 0;
    }

}

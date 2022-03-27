package com.projex.portal.dao;

import com.projex.portal.vo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User getUser(Integer userId);

    User getUserByUsername(String username);

    Integer updateTokenByUserId(Integer userId, String token);

    Integer register(String username, String password);

    Integer updateRecentCompany(Integer userId, Integer companyId);
}

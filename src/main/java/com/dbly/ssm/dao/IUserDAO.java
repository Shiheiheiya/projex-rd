package com.dbly.ssm.dao;

import com.dbly.ssm.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDAO {
    void addUser(User user);

    User findUserByName(User user);

    List<User> findAllUsers();

    User findUserById(Integer id);

    void updateUser(User user);

    void deleteUser(Integer id);

}

package com.projex.portal.dao;

import com.projex.portal.vo.UserProj;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProjDao {
    Integer insert(Integer projId, Integer userId, String username, String addDate);

    List<UserProj> getUsersByProjId(Integer projId);

    UserProj selectByProjAndUserId(Integer projId, Integer userId);
}

package com.projex.portal.mapper;

import com.projex.portal.vo.UserResult;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    UserResult getUser(Integer userId);
}

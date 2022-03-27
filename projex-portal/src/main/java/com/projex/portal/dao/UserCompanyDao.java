package com.projex.portal.dao;

import com.projex.portal.vo.UserCompany;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCompanyDao {
    Integer insert(Integer userId, Integer companyId, String username, String companyName, String addDate);

    Integer delByCompanyId(Integer companyId);

    UserCompany selectByCompanyIdAndUserId(Integer companyId, Integer userId);
}

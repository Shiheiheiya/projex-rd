package com.projex.portal.dao;

import com.projex.portal.vo.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyDao{
    Company selectByCompanyName(String companyName);

    Company getCompanyInfoById(Integer companyId);

    Integer insert(String name, String dsc, Integer owner);

    List<Company> getCompaniesByUserId(Integer userId);

    Integer delCompanyById(Integer companyId, Integer companyOwner);
}

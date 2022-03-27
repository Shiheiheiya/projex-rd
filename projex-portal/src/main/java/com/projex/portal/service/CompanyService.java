package com.projex.portal.service;

import com.projex.portal.api.CompanyResult;
import com.projex.portal.dao.CompanyDao;
import com.projex.portal.dao.UserCompanyDao;
import com.projex.portal.dao.UserDao;
import com.projex.portal.vo.Company;
import com.projex.portal.vo.UserCompany;
import com.projex.portal.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private UserCompanyDao userCompanyDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    /**
     * 创建企业
     * @param name
     * @param dsc
     * @param owner
     * @return
     */
    public CompanyResult createCompany(String name, String dsc, Integer owner) {
        Company company = companyDao.selectByCompanyName(name);
        if(company != null) return CompanyResult.builder().company(company).result(false).message("企业已存在").build();
        Integer i = companyDao.insert(name, dsc, owner);
        Company company1 = companyDao.selectByCompanyName(name);
        Boolean result = insertCompanyUser(company1.getCompanyId(), owner, company1.getCompanyName());
        return CompanyResult.builder().company(company1).result(i>0 & result).message("创建成功").build();
    }

    /**
     * 添加企业成员
     * @param companyId
     * @param userId
     * @param companyName
     * @return
     */
    public Boolean insertCompanyUser(Integer companyId, Integer userId, String companyName){
        UserCompany userCompany = userCompanyDao.selectByCompanyIdAndUserId(companyId, userId);
        if(userCompany != null) return false;
        java.sql.Date addDate = new java.sql.Date(new Date().getTime());
        User user = userDao.getUser(userId);
        Integer i = userCompanyDao.insert(userId, companyId, user.getUsername(), companyName, addDate.toString());
        return (i > 0);
    }
    /**
     * 通过企业名称获取企业信息
     * @param name
     * @return
     */
    public Company getCompanyByName(String name) {
        Company company = companyDao.selectByCompanyName(name);
        if (company != null){
            company.setOwner(userService.getUserById(company.getCompanyOwner()));
        }
        return company;
    }

    /**
     * 通过企业id获取企业信息
     * @param companyId
     * @return
     */
    public Company getCompanyById(Integer companyId) {
        Company company = companyDao.getCompanyInfoById(companyId);
        if (company != null){
            company.setOwner(userService.getUserById(company.getCompanyOwner()));
        }
        return company;
    }

    /**
     * 通过userId获取企业列表
     * @param userId
     * @return
     */
    public List<Company> getCompaniesByUserId(Integer userId) {
        return companyDao.getCompaniesByUserId(userId);
    }

    /**
     * 删除企业，只有企业拥有者可以删除企业
     * @param companyId
     * @param companyOwner
     * @return
     */
    public Boolean delCompanyById(Integer companyId, Integer companyOwner){
        Integer i = companyDao.delCompanyById(companyId, companyOwner);
        Integer j = userCompanyDao.delByCompanyId(companyId);
        return !(i <= 0 | j <= 0);
    }
}

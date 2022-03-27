package com.projex.portal.controller;

import com.projex.portal.service.UserService;
import com.projex.portal.vo.Company;
import com.projex.portal.api.CommonResult;
import com.projex.portal.api.CompanyResult;
import com.projex.portal.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@Api(tags = "CompanyController")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping(value = "/create")
    public CommonResult<CompanyResult> createCompany(@RequestParam String companyName,
                                              String companyDsc,
                                              @RequestParam Integer companyOwner){
        CompanyResult result = companyService.createCompany(companyName, companyDsc != null ? companyDsc : "", companyOwner);
        Boolean i = userService.updateRecentCompany(result.getCompany().getCompanyOwner(), result.getCompany().getCompanyId());
        return CommonResult.success(result, "success and update recent company " + i);
    }

    @ResponseBody
    @GetMapping(value = "/getCompanyById")
    public CommonResult<Company> getCompanyById(@RequestParam Integer userId,
                                                @RequestParam Integer companyId){
        Company company = companyService.getCompanyById(companyId);
        if (company != null){
            Boolean result = userService.updateRecentCompany(userId, companyId);
        }
        return CommonResult.success(company, "success");
    }

    @ResponseBody
    @GetMapping(value = "/getCompaniesByUserId")
    @ApiOperation("获取我加入的企业列表")
    public CommonResult<List<Company>> getCompaniesByUserId(@RequestParam Integer userId) {
        List<Company> companies = companyService.getCompaniesByUserId(userId);
        return CommonResult.success(companies, "success");
    }

    @ResponseBody
    @GetMapping(value = "/deleteCompanyById")
    public CommonResult<Boolean> delCompanyById(@RequestParam Integer companyId,
                                         @RequestParam Integer companyOwner) {
        boolean result = companyService.delCompanyById(companyId, companyOwner);
        return CommonResult.success(result, "success");
    }
}

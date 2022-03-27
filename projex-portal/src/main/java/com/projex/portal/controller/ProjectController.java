package com.projex.portal.controller;

import com.projex.portal.api.CommonResult;
import com.projex.portal.api.CompanyResult;
import com.projex.portal.api.ProjectResult;
import com.projex.portal.service.CompanyService;
import com.projex.portal.service.ProjectService;
import com.projex.portal.vo.Project;
import com.projex.portal.vo.UserProj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proj")
@Api(tags = "ProjectController")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @ResponseBody
    @PostMapping(value = "/create")
    @ApiOperation("创建项目")
    public CommonResult<ProjectResult> createProject(@RequestParam Integer companyId,
                                                     @RequestParam String projName,
                                                     String projDsc,
                                                     @RequestParam Integer projOwner,
                                                     String beginDate,
                                                     String endDate){
        ProjectResult result = projectService.createProject(projName, projDsc != null ? projDsc : "", projOwner,
                beginDate, endDate, companyId);
        return CommonResult.success(result, "success");
    }

    @ResponseBody
    @GetMapping(value = "/getUserByProjId")
    @ApiOperation("获取项目成员")
    public CommonResult<List<UserProj>> getUserByProjId(@RequestParam Integer projId) {
        List<UserProj> users = projectService.getUserByProjId(projId);
        return CommonResult.success(users, "success");
    }

    @ResponseBody
    @PostMapping(value = "addProjUser")
    @ApiOperation("添加项目成员")
    public CommonResult<Boolean> addProjUser(@RequestParam Integer projId,
                                             @RequestParam Integer userId) {
        Boolean result = projectService.insertProjUser(projId, userId);
        return CommonResult.success(result, "success");
    }

    @ResponseBody
    @GetMapping(value = "getAllProjList")
    @ApiOperation("获取企业下的所有项目")
    public CommonResult<List<Project>> getAllProjList(@RequestParam Integer companyId) {
        List<Project> projects = projectService.getAllProjList(companyId);
        return CommonResult.success(projects, "success");
    }

    @ResponseBody
    @GetMapping(value = "getMyAddProjList")
    @ApiOperation("获取所有我加入的项目")
    public CommonResult<List<Project>> getMyAddProjList(@RequestParam Integer companyId,
                                                           @RequestParam Integer userId) {
        List<Project> projects = projectService.getMyAddProjList(companyId, userId);
        return CommonResult.success(projects, "success");
    }

    @ResponseBody
    @GetMapping(value = "getMyProjList")
    @ApiOperation("获取我创建的项目")
    public CommonResult<List<Project>> getMyProjList(@RequestParam Integer companyId,
                                                      @RequestParam Integer userId) {
        List<Project> projects = projectService.getMyProjList(companyId, userId);
        return CommonResult.success(projects, "success");
    }

    @ResponseBody
    @GetMapping(value = "getProjInfoById")
    @ApiOperation("获取项目信息")
    public CommonResult<Project> getProjInfoById(@RequestParam Integer projId){
        Project project = projectService.getProjInfoById(projId);
        return CommonResult.success(project, "success");
    }

    @ResponseBody
    @PostMapping(value = "updateProjNews")
    @ApiOperation("更新项目公告信息")
    public CommonResult<Boolean> updateProjNews(@RequestParam Integer projId,
                                       @RequestParam String projNews){
        Boolean result = projectService.updateProjNews(projId, projNews);
        return CommonResult.success(result, "success");
    }
}

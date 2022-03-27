package com.projex.portal.service;

import com.projex.portal.api.ProjectResult;
import com.projex.portal.dao.ProjectDao;
import com.projex.portal.dao.UserDao;
import com.projex.portal.dao.UserProjDao;
import com.projex.portal.vo.Project;
import com.projex.portal.vo.UserProj;
import com.projex.portal.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private UserProjDao userProjDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    /**
     * 创建项目
     * @return
     */
    public ProjectResult createProject(String projName, String projDsc, Integer projOwner,
                                       String beginDate, String endDate, Integer companyId) {
        // 项目已存在
        Project project = projectDao.getProjectByName(projName);
        if (project != null) return ProjectResult.builder().project(project).result(false).message("项目已存在").build();
        // 项目不存在进行创建
        java.sql.Date createTime = new java.sql.Date(new Date().getTime());
        Integer i = projectDao.insert(projName, projDsc, projOwner, beginDate, endDate, companyId, "未开始", "", createTime.toString());
        Project project1 = projectDao.getProjectByName(projName);
        Boolean result = insertProjUser(project1.getProjId(), projOwner);
        return ProjectResult.builder().project(project1).result(i > 0 & result).message("创建成功").build();
    }

    /**
     * 通过项目id获取项目成员列表
     * @param projId
     * @return
     */
    public List<UserProj> getUserByProjId(Integer projId) {
        return userProjDao.getUsersByProjId(projId);
    }

    /**
     * 添加项目成员
     * @param projId
     * @param userId
     * @return
     */
    public Boolean insertProjUser(Integer projId, Integer userId) {
        UserProj userProj = userProjDao.selectByProjAndUserId(projId, userId);
        if (userProj != null) return false;
        java.sql.Date addDate = new java.sql.Date(new Date().getTime());
        User user = userDao.getUser(userId);
        Integer i = userProjDao.insert(projId, userId, user.getUsername(), addDate.toString());
        return (i > 0);
    }

    /**
     * 获取企业下的所有项目
     * @param companyId
     * @return
     */
    public List<Project> getAllProjList(Integer companyId){
        return projectDao.getAllProjList(companyId);
    }

    /**
     * 获取我加入的项目
     * @param companyId
     * @param userId
     * @return
     */
    public List<Project> getMyAddProjList(Integer companyId, Integer userId) {
        return projectDao.getMyAddProjList(companyId, userId);
    }

    /**
     * 获取我创建的项目
     * @param companyId
     * @param ownerId
     * @return
     */
    public  List<Project> getMyProjList(Integer companyId, Integer ownerId) {
        return projectDao.getMyProjList(companyId, ownerId);
    }

    /**
     * 通过id获取项目信息
     * @param projId
     * @return
     */
    public Project getProjInfoById(Integer projId){
        Project project = projectDao.getProjInfoById(projId);
        if (project != null){
            project.setOwner(userService.getUserById(project.getProjOwner()));
        }
        return project;
    }

    /**
     * 更新项目公告
     * @param projId
     * @param projNews
     * @return
     */
    public Boolean updateProjNews(Integer projId, String projNews){
        Integer i = projectDao.updateProjNews(projId, projNews);
        return i>0;
    }
}

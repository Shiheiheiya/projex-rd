package com.projex.portal.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.projex.portal.dao.WorkitemDao;
import com.projex.portal.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WorkitemService {
    @Autowired
    private WorkitemDao workitemDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ScrumService scrumService;

    @Autowired
    private ProjectService projectService;

    /**
     * 获取对应分类的工作项列表
     * @param projId
     * @param label
     * @return
     */
    public WorkitemList getWorkitemListByLabel(Integer projId, String label, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Workitem> workitemList = workitemDao.getWorkitemListByLabel(projId, label);
        PageInfo<Workitem> pageInfo = new PageInfo<>(workitemList);
        List<Workitem> list = pageInfo.getList();
        for (Workitem item : list){
            if (item != null){
                item.setDirector(userService.getUserById(item.getWorkitemDirector()));
                item.setCreator(userService.getUserById(item.getWorkitemCreator()));
                item.setScrum(scrumService.getScrumById(item.getScrumId()));
                item.setProject(projectService.getProjInfoById(item.getProjId()));
            }
        }
        return WorkitemList.builder().pageNum(pageNum).pageSize(pageSize).pages(pageInfo.getPages()).total(pageInfo.getTotal()).workitemList(list).build();
    }

    /**
     * 获取工作项详情
     * @param workitemId
     * @return
     */
    public Workitem getWorkitemById(Integer workitemId){
        Workitem workitem = workitemDao.getWorkitemById(workitemId);
        if (workitem != null){
            workitem.setDirector(userService.getUserById(workitem.getWorkitemDirector()));
            workitem.setCreator(userService.getUserById(workitem.getWorkitemCreator()));
            workitem.setScrum(scrumService.getScrumById(workitem.getScrumId()));
            workitem.setProject(projectService.getProjInfoById(workitem.getProjId()));
        }
        return workitem;
    }

    /**
     * 新建工作项
     * @return
     */
    public Boolean create(String label, String name, String dsc, String level, Integer director,
                          Integer creator, Double estimateTime, Integer scrumId, Integer projId){
        java.sql.Date createTime = new java.sql.Date(new Date().getTime());
        Integer i = workitemDao.insert(label, name, dsc, "待处理", level, director, creator, createTime.toString(), estimateTime, scrumId, projId);
        return (i > 0);
    }

    /**
     * 更新工作项描述
     * @param workitemId
     * @return
     */
    public Boolean updateWorkitemInfo(Integer workitemId, String name, String dsc, String status,
                                     String level, Integer director, Double estimateTime,
                                     Double trueTime, Integer scrumId, Integer projId){
        String finishTime = null;
        if (status.equals("已完成")){
            java.sql.Date now = new java.sql.Date(new Date().getTime());
            finishTime = now.toString();
        }
        Integer i = workitemDao.updateWorkitemInfo(workitemId, name, dsc, status, level, director, estimateTime, trueTime, scrumId, finishTime, projId);
        return i>0;
    }

    /**
     * 删除工作项
     * @param workitemId
     * @return
     */
    public Boolean delWorkitemById(Integer workitemId){
        Integer i = workitemDao.delWorkitemById(workitemId);
        return i>0;
    }

    /**
     * 获取工作项各类型的未完成数量
     * @param projId
     * @return
     */
    public ProjOverviewWorkitemList getNoFinishNum(Integer projId){
        ProjOverviewWorkitemList result = new ProjOverviewWorkitemList();
        result.setNoFinishReq(workitemDao.getNoFinishNum(projId, "需求"));
        result.setNoFinishTask(workitemDao.getNoFinishNum(projId, "任务"));
        result.setNoFinishBug(workitemDao.getNoFinishNum(projId, "缺陷"));
        return result;
    }
}

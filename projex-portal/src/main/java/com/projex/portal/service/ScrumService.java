package com.projex.portal.service;

import com.projex.portal.dao.ScrumDao;
import com.projex.portal.vo.Scrum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class ScrumService {
    @Autowired
    private ScrumDao scrumDao;

    @Autowired
    private UserService userService;

    /**
     * 获取项目下的迭代列表
     * @param projId
     * @return
     */
    public List<Scrum> getScrumListByProjId(Integer projId){
        return scrumDao.getScrumListByProjId(projId);
    }

    /**
     * 创建迭代
     * @return
     */
    public Boolean insertScrum(String name, String dsc, Integer creator,
                               String startTime, String endTime, Integer projId){
        Scrum scrum = getScrumByName(name);
        if (scrum != null){ // 存在重名的迭代
            return false;
        }
        java.sql.Date createTime = new java.sql.Date(new Date().getTime());
        Integer i = scrumDao.insertScrum(name, "未开始", dsc, creator, createTime.toString(), startTime, endTime, null, projId);
        return i>0;
    }

    /**
     * 根据名称获取
     * @param scrumName
     * @return
     */
    public Scrum getScrumByName(String scrumName){
        Scrum scrum = scrumDao.getScrumInfoByName(scrumName);
        if(scrum != null){
            scrum.setCreator(userService.getUserById(scrum.getScrumCreator()));
        }
        return scrum;
    }

    /**
     * 根据Id获取
     * @param scrumId
     * @return
     */
    public Scrum getScrumById(Integer scrumId){
        Scrum scrum = scrumDao.getScrumInfoById(scrumId);
        if(scrum != null){
            scrum.setCreator(userService.getUserById(scrum.getScrumCreator()));
        }
        return scrum;
    }

    /**
     * 更新迭代信息
     */
    public Boolean updateScrumInfo(Integer scrumId, String name, String status, String dsc,
                                   String startTime, String endTime){
        Scrum scrumById = getScrumById(scrumId);
        Scrum scrumByName = getScrumByName(name);
        if (!name.equals(scrumById.getScrumName()) && name.equals(scrumByName.getScrumName())){
            return false; // 不允许更新为重名的迭代
        }
        String finishTime = scrumById.getScrumFinishTime();
        if (status.equals("已完成")){
            java.sql.Date nowTime = new java.sql.Date(new Date().getTime());
            finishTime = nowTime.toString();
        }
        Integer i = scrumDao.updateScrumInfo(scrumId, name, status, dsc, startTime, endTime, finishTime);
        return i>0;
    }

    /**
     * 删除迭代
     * @param scrumId
     * @return
     */
    public Boolean delScrumById(Integer scrumId){
        Integer i = scrumDao.delScrumById(scrumId);
        return i>0;
    }
}

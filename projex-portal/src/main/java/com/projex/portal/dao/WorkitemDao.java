package com.projex.portal.dao;

import com.projex.portal.vo.ProjOverviewWorkitemList;
import com.projex.portal.vo.UserProj;
import com.projex.portal.vo.Workitem;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface WorkitemDao {
    List<Workitem> getWorkitemListByLabel(Integer projId, String label);

    Integer insert(String label, String name, String dsc, String status, String level,
                   Integer director, Integer creator, String createTime, Double estimateTime,
                   Integer scrumId, Integer projId);

    Workitem getWorkitemById(Integer workitemId);

    Integer updateWorkitemInfo(Integer workitemId, String name, String dsc, String status,
                               String level, Integer director, Double estimateTime,
                               Double trueTime, Integer scrumId, String finishTime);

    Integer delWorkitemById(Integer workitemId);

    Integer getNoFinishNum(Integer projId, String label);
}

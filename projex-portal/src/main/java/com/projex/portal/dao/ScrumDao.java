package com.projex.portal.dao;

import com.projex.portal.vo.Scrum;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface ScrumDao {
    List<Scrum> getScrumListByProjId(Integer projId);

    Integer insertScrum(String name, String status, String dsc, Integer creator,
                        String createTime, String startTime, String endTime,
                        String finishTime, Integer projId);

    Scrum getScrumInfoByName(String scrumName);

    Scrum getScrumInfoById(Integer scrumId);

    Integer updateScrumInfo(Integer scrumId, String name, String status, String dsc,
                            String startTime, String endTime, String finishTime);

    Integer delScrumById(Integer scrumId);

    // scrumSummary
    Integer getTotalWorkitemByScrumId(Integer scrumId);
    Integer getDoneWorkitemByScrumId(Integer scrumId);
    Double getEstimateTimeByScrumId(Integer scrumId);
    Double getTrueTimeByScrumId(Integer scrumId);

    // 燃尽图
    // 获取存量数据
    Integer getNoFinishCountByDate(Integer scrumId, String label, String date);
    // 获取新增数据
    Integer getCreateCountByDate(Integer scrumId, String label, String date);
    // 获取完成数据
    Integer getFinishCountByDate(Integer scrumId, String label, String date);
}

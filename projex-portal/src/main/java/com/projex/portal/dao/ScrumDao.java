package com.projex.portal.dao;

import com.projex.portal.vo.Scrum;
import org.springframework.stereotype.Repository;

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
}

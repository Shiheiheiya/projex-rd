package com.projex.portal.dao;

import com.projex.portal.vo.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDao {

    Integer insert (String projName, String projDsc, Integer projOwner,
                    String beginDate, String endDate, Integer companyId,
                    String projStatus, String projNews, String createTime);

    Project getProjectByName (String projName);

    List<Project> getAllProjList(Integer companyId);

    List<Project> getMyAddProjList(Integer companyId, Integer userId);

    List<Project> getMyProjList(Integer companyId, Integer ownerId);

    Project getProjInfoById(Integer projId);

    Integer updateProjNews(Integer projId, String projNews);
}

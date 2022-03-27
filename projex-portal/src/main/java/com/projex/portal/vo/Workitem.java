package com.projex.portal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Workitem implements Serializable {
    private Integer workitemId;
    private String workitemLabel;
    private String workitemName;
    private String workitemDsc;
    private String workitemStatus;
    private String workitemLevel;
    private Integer workitemDirector;
    private Integer workitemCreator;
    private String workitemCreateTime;
    private String workitemFinishTime;
    private Double workitemEstimateTime;
    private Double workitemTrueTime;
    private Integer scrumId;
    private Integer projId;
    private Integer isDelete;

    private User director;
    private User creator;
    private Scrum scrum;
    private Project project;
}

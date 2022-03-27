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
public class Scrum implements Serializable {
    private Integer scrumId;
    private String scrumName;
    private String scrumStatus;
    private String scrumDsc;
    private Integer scrumCreator;
    private String scrumCreateTime;
    private String scrumStartTime;
    private String scrumEndTime;
    private String scrumFinishTime;
    private Integer projId;
    private Integer isDelete;
    private User creator;
}

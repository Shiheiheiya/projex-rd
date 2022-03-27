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
public class Project implements Serializable {
    private Integer projId;
    private Integer companyId;
    private String projName;
    private String projStatus;
    private String projDsc;
    private Integer projOwner;
    private String projNews;
    private String createTime;
    private String beginDate;
    private String endDate;
    private Integer isDelete;
    private User owner;
}

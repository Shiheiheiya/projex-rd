package com.projex.portal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkitemList implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
    private Integer pages;
    private Long total;
    private List<Workitem> workitemList;
}

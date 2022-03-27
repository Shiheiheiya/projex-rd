package com.projex.portal.api;

import com.projex.portal.vo.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResult implements Serializable {
    private Project project;
    private Boolean result;
    private String message;
}

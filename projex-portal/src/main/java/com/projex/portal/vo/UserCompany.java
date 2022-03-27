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
public class UserCompany implements Serializable {
    private Integer userId;
    private Integer companyId;
    private String username;
    private String companyName;
    private String addDate;
}

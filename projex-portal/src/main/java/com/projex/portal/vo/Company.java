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
public class Company implements Serializable {
    private Integer companyId;
    private String companyName;
    private String companyDescription;
    private Integer companyOwner;
    private Integer isDelete;
    private User owner;
}

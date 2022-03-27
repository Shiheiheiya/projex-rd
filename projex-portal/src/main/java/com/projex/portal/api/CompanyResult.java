package com.projex.portal.api;

import com.projex.portal.vo.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResult implements Serializable {
    private Company company;
    private Boolean result;
    private String message;

}

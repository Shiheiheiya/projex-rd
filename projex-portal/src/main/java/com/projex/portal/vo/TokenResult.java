package com.projex.portal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResult implements Serializable {
    private int userId;
    private String username;
    private Date tokenBeginTime;
    private Date tokenEndTime;
}

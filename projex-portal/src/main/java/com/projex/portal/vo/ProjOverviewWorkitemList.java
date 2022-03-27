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
public class ProjOverviewWorkitemList implements Serializable {
    private Integer noFinishReq;
    private Integer noFinishTask;
    private Integer noFinishBug;
}

package com.projex.portal.controller;

import com.projex.portal.api.CommonResult;
import com.projex.portal.service.WorkitemService;
import com.projex.portal.vo.ProjOverviewWorkitemList;
import com.projex.portal.vo.Workitem;
import com.projex.portal.vo.WorkitemList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workitem")
@Api(tags = "WorkitemController")
public class WorkitemController {

    @Autowired
    private WorkitemService workitemService;

    @ResponseBody
    @GetMapping(value = "/getWorkitemByLabel")
    @ApiOperation("获取工作项列表")
    public CommonResult<WorkitemList> getWorkitemByLabel(@RequestParam Integer projId, String label,
                                                           @RequestParam(defaultValue = "1") Integer pageNum,
                                                           @RequestParam(defaultValue = "20") Integer pageSize){
        if (label == null) label = "需求";
        WorkitemList workitemList = workitemService.getWorkitemListByLabel(projId, label, pageNum, pageSize);
        return CommonResult.success(workitemList, "success");
    }

    @ResponseBody
    @GetMapping(value = "/getWorkitemById")
    @ApiOperation("获取工作项详情")
    public CommonResult<Workitem> getWorkitemById(@RequestParam Integer workitemId){
        Workitem workitem = workitemService.getWorkitemById(workitemId);
        return CommonResult.success(workitem, "success");
    }

    @ResponseBody
    @PostMapping(value = "/createWorkitem")
    @ApiOperation("新建工作项")
    public CommonResult<Boolean> createWorkitem(@RequestParam String label, @RequestParam String name,
                                                String dsc, String level, Integer director,
                                                @RequestParam Integer creator, Double estimateTime,
                                                Integer scrumId, @RequestParam Integer projId){
        if (level == null) level = "中";
        if (director == null) director = creator;
        Boolean result = workitemService.create(label, name, dsc, level, director, creator, estimateTime, scrumId, projId);
        return CommonResult.success(result, "success");
    }

    @ResponseBody
    @PostMapping(value = "/updateWorkitemInfo")
    @ApiOperation("更新工作项信息")
    public CommonResult<Boolean> updateWorkitemInfo(@RequestParam Integer workitemId,
                                                   @RequestParam String name, @RequestParam String dsc,
                                                   @RequestParam String status, @RequestParam String level,
                                                   @RequestParam Integer director, @RequestParam Double estimateTime,
                                                   @RequestParam Double trueTime, @RequestParam Integer scrumId,
                                                    @RequestParam Integer projId){
        if (name == null) name = "null";
        Boolean result = workitemService.updateWorkitemInfo(workitemId, name, dsc, status, level, director, estimateTime, trueTime, scrumId, projId);
        return CommonResult.success(result, "success");
    }

    @ResponseBody
    @PostMapping(value = "/delWorkitemById")
    @ApiOperation("删除工作项")
    public CommonResult<Boolean> delWorkitemById(@RequestParam Integer workitemId){
        Boolean result = workitemService.delWorkitemById(workitemId);
        return CommonResult.success(result, "success");
    }

    @ResponseBody
    @PostMapping(value = "/getNoFinishNum")
    @ApiOperation("获取工作项各类型的未完成数量")
    public CommonResult<ProjOverviewWorkitemList> getNoFinishNum(@RequestParam Integer projId){
        ProjOverviewWorkitemList result = workitemService.getNoFinishNum(projId);
        return CommonResult.success(result, "success");
    }

}

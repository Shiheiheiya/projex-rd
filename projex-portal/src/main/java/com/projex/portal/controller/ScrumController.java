package com.projex.portal.controller;

import com.projex.portal.api.CommonResult;
import com.projex.portal.api.ProjectResult;
import com.projex.portal.service.ScrumService;
import com.projex.portal.service.UserService;
import com.projex.portal.vo.Scrum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scrum")
@Api(tags = "ScrumController")
public class ScrumController {
    @Autowired
    private ScrumService scrumService;

    @ResponseBody
    @GetMapping(value = "/getScrumListByProjId")
    @ApiOperation("获取项目下的迭代列表")
    public CommonResult<List<Scrum>> getScrumListByProjId(@RequestParam Integer projId){
        List<Scrum> scrumList = scrumService.getScrumListByProjId(projId);
        return CommonResult.success(scrumList, "success");
    }

    @ResponseBody
    @PostMapping(value = "/insertScrum")
    @ApiOperation("创建迭代")
    public CommonResult<Scrum> insertScrum(@RequestParam String name, String dsc, @RequestParam Integer creator,
                                           @RequestParam String startTime, @RequestParam String endTime, @RequestParam Integer projId){
        Boolean result = scrumService.insertScrum(name, dsc, creator, startTime, endTime, projId);
        if (result){
            Scrum scrum = scrumService.getScrumByName(name);
            return CommonResult.success(scrum, "success");
        }
        return CommonResult.failed("已存在重名迭代");
    }

    @ResponseBody
    @GetMapping(value = "/getScrumInfoById")
    @ApiOperation("根据Id获取迭代信息")
    public CommonResult<Scrum> getScrumInfoById(@RequestParam Integer scrumId){
        Scrum scrum = scrumService.getScrumById(scrumId);
        return CommonResult.success(scrum, "success");
    }

    @ResponseBody
    @PostMapping(value = "/updateScrumInfo")
    @ApiOperation("更新迭代信息")
    public CommonResult<Scrum> updateScrumInfo(@RequestParam Integer scrumId, @RequestParam String name, @RequestParam String status,
                                               @RequestParam String dsc, @RequestParam String startTime, @RequestParam String endTime){
        Boolean result = scrumService.updateScrumInfo(scrumId, name, status, dsc, startTime, endTime);
        if (result) {
            Scrum scrum = scrumService.getScrumById(scrumId);
            return CommonResult.success(scrum, "success");
        }
        return CommonResult.failed("修改迭代信息失败");
    }

    @ResponseBody
    @PostMapping(value = "/delScrumById")
    @ApiOperation("删除迭代")
    public CommonResult<Boolean> delScrumById(@RequestParam Integer scrumId){
        Boolean result = scrumService.delScrumById(scrumId);
        return CommonResult.success(result);
    }

    @ResponseBody
    @GetMapping(value = "/getScrumSummary")
    @ApiOperation("获取迭代汇总数据")
    public CommonResult<Map<String, Object>> getScrumSummary(@RequestParam Integer scrumId){
        Map<String, Object> scrumSummary = scrumService.getScrumSummary(scrumId);
        return CommonResult.success(scrumSummary, "success");
    }

    @ResponseBody
    @GetMapping(value = "/getBurnDownByLabel")
    @ApiOperation("获取燃尽图数据")
    public CommonResult<Map<String, List<List<Object>>>> getBurnDownByLabel(@RequestParam Integer scrumId,
                                                                @RequestParam String label){
        try {
            Map<String, List<List<Object>>> result = scrumService.getBurnDownByLabel(scrumId, label);
            if (result == null) return CommonResult.failed("false");
            return CommonResult.success(result, "success");
        } catch (ParseException e) {
            e.printStackTrace();
            return CommonResult.failed("false");
        }
    }

    @ResponseBody
    @GetMapping(value = "/getWorkitemChangeByLabel")
    @ApiOperation("获取工作项趋势图数据")
    public CommonResult<Map<String, List<?>>> getWorkitemChangeByLabel(@RequestParam Integer scrumId,
                                                                            @RequestParam String label){
        try {
            Map<String, List<?>> result = scrumService.getWorkitemChangeByLabel(scrumId, label);
            if (result == null) return CommonResult.failed("false");
            return CommonResult.success(result, "success");
        } catch (ParseException e) {
            e.printStackTrace();
            return CommonResult.failed("false");
        }
    }
}

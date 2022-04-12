package com.projex.portal.service;

import com.projex.portal.dao.ScrumDao;
import com.projex.portal.utils.DateTools;
import com.projex.portal.vo.Scrum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class ScrumService {
    @Autowired
    private ScrumDao scrumDao;

    @Autowired
    private UserService userService;

    /**
     * 获取项目下的迭代列表
     * @param projId
     * @return
     */
    public List<Scrum> getScrumListByProjId(Integer projId){
        List<Scrum> scrumList = scrumDao.getScrumListByProjId(projId);
        for (Scrum item : scrumList){
            item.setCreator(userService.getUserById(item.getScrumCreator()));
        }
        return scrumList;
    }

    /**
     * 创建迭代
     * @return
     */
    public Boolean insertScrum(String name, String dsc, Integer creator,
                               String startTime, String endTime, Integer projId){
        Scrum scrum = getScrumByName(name);
        if (scrum != null){ // 存在重名的迭代
            return false;
        }
        java.sql.Date createTime = new java.sql.Date(new Date().getTime());
        Integer i = scrumDao.insertScrum(name, "未开始", dsc, creator, createTime.toString(), startTime, endTime, null, projId);
        return i>0;
    }

    /**
     * 根据名称获取
     * @param scrumName
     * @return
     */
    public Scrum getScrumByName(String scrumName){
        Scrum scrum = scrumDao.getScrumInfoByName(scrumName);
        if(scrum != null){
            scrum.setCreator(userService.getUserById(scrum.getScrumCreator()));
        }
        return scrum;
    }

    /**
     * 根据Id获取
     * @param scrumId
     * @return
     */
    public Scrum getScrumById(Integer scrumId){
        Scrum scrum = scrumDao.getScrumInfoById(scrumId);
        if(scrum != null){
            scrum.setCreator(userService.getUserById(scrum.getScrumCreator()));
        }
        return scrum;
    }

    /**
     * 更新迭代信息
     */
    public Boolean updateScrumInfo(Integer scrumId, String name, String status, String dsc,
                                   String startTime, String endTime){
        Scrum scrumById = getScrumById(scrumId);
        Scrum scrumByName = getScrumByName(name);
        if (!name.equals(scrumById.getScrumName()) && name.equals(scrumByName.getScrumName())){
            return false; // 不允许更新为重名的迭代
        }
        String finishTime = scrumById.getScrumFinishTime();
        if (status.equals("已完成")){
            java.sql.Date nowTime = new java.sql.Date(new Date().getTime());
            finishTime = nowTime.toString();
        }
        Integer i = scrumDao.updateScrumInfo(scrumId, name, status, dsc, startTime, endTime, finishTime);
        return i>0;
    }

    /**
     * 删除迭代
     * @param scrumId
     * @return
     */
    public Boolean delScrumById(Integer scrumId){
        Integer i = scrumDao.delScrumById(scrumId);
        return i>0;
    }

    /**
     * 获取迭代汇总数据
     * @param scrumId
     * @return
     */
    public Map<String, Object> getScrumSummary(Integer scrumId){
        Map<String, Object> result = new HashMap<>();
        Integer totalWorkitem = scrumDao.getTotalWorkitemByScrumId(scrumId);
        Integer doneWorkitem = scrumDao.getDoneWorkitemByScrumId(scrumId);
        Double estimateTime = scrumDao.getEstimateTimeByScrumId(scrumId);
        Double trueTime = scrumDao.getTrueTimeByScrumId(scrumId);
        result.put("totalWorkitem", totalWorkitem == null ? 0 : totalWorkitem);
        result.put("doneWorkitem", doneWorkitem == null ? 0 : doneWorkitem);
        result.put("estimateTime", estimateTime == null ? 0.0 : estimateTime);
        result.put("trueTime", trueTime == null ? 0.0 : trueTime);
        return result;
    }

    /**
     * 获取燃尽图的数据
     * @param scrumId
     * @param label
     * @return
     */
    public Map<String, List<List<Object>>> getBurnDownByLabel(Integer scrumId, String label) throws ParseException {
        Scrum scrum = scrumDao.getScrumInfoById(scrumId);
        String startTime = scrum.getScrumStartTime();
        String endTime = scrum.getScrumEndTime();
        if (startTime == null || endTime == null) return null;
        List<String> dateList = DateTools.getDateList(startTime, endTime); // 日期列表
        List<List<Object>> stock = new ArrayList<>(); // 存量数据
        List<List<Object>> wish = new ArrayList<>(); // 期望数据
        Map<String, List<List<Object>>> result = new HashMap<>(); // 返回的结果数据集
        double firstPoint = 0.0; // 第一天的起始点
        long days = DateTools.getDiffByDate(startTime, endTime); // 时间区间跨度
        int i = 0; // 递减天数
        for (String date : dateList) {
            Integer count = scrumDao.getNoFinishCountByDate(scrumId, label, date);
            // 处理存量数据
            List<Object> item = new ArrayList<>();
            item.add(date);
            item.add(count);
            stock.add(item);
            // 处理期望数据
            List<Object> item1 = new ArrayList<>();
            item1.add(date);
            if (date.equals(startTime)){ // 如果是第一天，加入期望值
                item1.add(count);
                firstPoint = count*1.0;
            }else if(date.equals(endTime)){ // 如果是最后一天，期望置0
                item1.add(0);
            }else { // 区间内的每日递减
                i++;
                item1.add(firstPoint - firstPoint / days * i);
            }
            wish.add(item1);
        }
        result.put("stock", stock);
        result.put("wish", wish);
        return result;
    }

    /**
     * 工作项趋势图
     * @param scrumId
     * @param label
     * @return
     */
    public Map<String, List<?>> getWorkitemChangeByLabel(Integer scrumId, String label) throws ParseException {
        Scrum scrum = scrumDao.getScrumInfoById(scrumId);
        String startTime = scrum.getScrumStartTime();
        String endTime = scrum.getScrumEndTime();
        if (startTime == null || endTime == null) return null;
        Map<String, List<?>> result = new HashMap<>();
        List<String> dateList = DateTools.getDateList(startTime, endTime);
        List<Integer> createList = new ArrayList<>();
        List<Integer> finishList = new ArrayList<>();
        for (String date : dateList){
            createList.add(scrumDao.getCreateCountByDate(scrumId, label, date));
            finishList.add(scrumDao.getFinishCountByDate(scrumId, label, date));
        }
        result.put("dateList", dateList);
        result.put("createList", createList);
        result.put("finishList", finishList);
        return result;
    }
}

package com.cloudyoung.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cloudyoung.xxx.model.Activity;

public interface ActivityMapper {
    int deleteByPrimaryKey(Long activityId);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Long activityId);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);
    
    List<Activity> findActivityByParams(Map<String, Object> paramsMap);
    
    List<Activity> getActivityEachRecords(Map<String, Object> paramsMap);
    /**
     * Description: 获取当前需要提示的活动信息
     * @Version1.0 2016年7月9日 上午10:44:58 by 杨雷（yanglei@cloud-young.com）创建
     * @return
     */
    Activity getNeedNoticeActivityInfo(Map<String, Object> paramsMap);
    
    /**
     * Description: 单元测试不通过
     * @Version1.0 2016年7月9日 下午6:24:33 by 代鹏（daipeng.456@gmail.com）创建
     * @param activitys
     * @return
     */
    @Deprecated
    int batchUpdate(@Param("activitys")List<Activity> activitys);
}
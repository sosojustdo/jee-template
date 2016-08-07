package com.cloudyoung.dao;

import java.util.List;
import java.util.Map;

import com.cloudyoung.xxx.model.ActivityItem;

public interface ActivityItemMapper {
    int deleteByPrimaryKey(Long activityItemId);

    int insert(ActivityItem record);

    int insertSelective(ActivityItem record);

    ActivityItem selectByPrimaryKey(Long activityItemId);

    int updateByPrimaryKeySelective(ActivityItem record);

    int updateByPrimaryKey(ActivityItem record);
    
    List<ActivityItem> findActivityItemByParams(Map<String, Object> params);
}
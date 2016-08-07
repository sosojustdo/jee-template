package com.cloudyoung.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cloudyoung.xxx.model.ActivityTakeRecord;

public interface ActivityTakeRecordMapper {
	int deleteByPrimaryKey(Long activityTakeRecordId);

    int insert(ActivityTakeRecord record);

    int insertSelective(ActivityTakeRecord record);

    ActivityTakeRecord selectByPrimaryKey(Long activityTakeRecordId);

    int updateByPrimaryKeySelective(ActivityTakeRecord record);

    int updateByPrimaryKey(ActivityTakeRecord record);
    
    List<ActivityTakeRecord> findActivityTakeRecordByParams(Map<String, Object> params);
    
    int batchSave(List<ActivityTakeRecord> takeRecords);
    
    List<String> findActivityPrizeCode(@Param("activityId")Long activityId);
    
}
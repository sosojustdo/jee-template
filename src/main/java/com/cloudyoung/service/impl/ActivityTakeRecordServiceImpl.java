package com.cloudyoung.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudyoung.dao.ActivityItemMapper;
import com.cloudyoung.dao.ActivityMapper;
import com.cloudyoung.dao.ActivityTakeRecordMapper;
import com.cloudyoung.enums.ActivityErrorCodeEnum;
import com.cloudyoung.enums.ActivityItemTypeEnum;
import com.cloudyoung.enums.ActivityItemWayEnum;
import com.cloudyoung.enums.ActivityPlayStatusEnum;
import com.cloudyoung.service.ActivityTakeRecordService;
import com.cloudyoung.xxx.model.Activity;
import com.cloudyoung.xxx.model.ActivityItem;
import com.cloudyoung.xxx.model.ActivityTakeRecord;
import com.cloudyoung.xxx.vo.ActivityResultVo;
import com.llb.cloudyoung.framework.tools.sequence.SequenceGenerator;
import com.llb.cloudyoung.framework.utils.ToolsUtil;

@Service("activityTakeRecordService")
public class ActivityTakeRecordServiceImpl implements ActivityTakeRecordService {
	
	//迪斯尼抽奖卷，卷码基数
	private static final Long lottery_unicode_base = 1000000L;
	
	@Autowired
	private ActivityTakeRecordMapper activityTakeRecordMapper;
	
	@Autowired
	private ActivityItemMapper activityItemMapper;
	
	@Autowired
	private ActivityMapper activityMapper;
	
	@Autowired
	private SequenceGenerator sequenceGenerator;

	@Override
	public ActivityResultVo recevieActivityLottery(Map<String, Object> params) {
		String platform = (String) params.get("platform");
		String deviceNo = (String) params.get("deviceNo");
		String userId = (String) params.get("userId");
		String mobile = (String) params.get("mobile");
		String lotteryGiveTypeStr = (String) params.get("lotteryGiveType");
		String activityIdStr = ((Long)params.get("activityId")).toString();
		ActivityResultVo resultVo = this.checkActivityLotteryBaseParams(userId, mobile, activityIdStr, lotteryGiveTypeStr);
		if(null != resultVo && !resultVo.getRet()){
			return resultVo;
		}
		
		Activity activity = activityMapper.selectByPrimaryKey(Long.valueOf(activityIdStr));
		if(null == activity){
			return new ActivityResultVo(false, ActivityErrorCodeEnum.ACTIVITY_ERR_10001.getMsg(), ActivityErrorCodeEnum.ACTIVITY_ERR_10001.getCode());
		}else{
			resultVo = this.checkActivityDateValid(activity, resultVo);
			if(!resultVo.getRet()){
				return resultVo;
			}
			List<ActivityItem> activityItems = activityItemMapper.findActivityItemByParams(ToolsUtil.builderMapParams("itemType", ActivityItemTypeEnum.LOTTERY.getType(), "itemWayType", ActivityItemWayEnum.getLotteryGiveEnumByCode(lotteryGiveTypeStr).getGiveCode()));
			List<Long> activityItemIds = null;
			if(CollectionUtils.isNotEmpty(activityItems)){
				activityItemIds = new ArrayList<Long>();
				for(ActivityItem item:activityItems){
					activityItemIds.add(item.getActivityItemId());
				}
			}else{
				return new ActivityResultVo(false, ActivityErrorCodeEnum.ACTIVITY_ERR_10009.getMsg(), ActivityErrorCodeEnum.ACTIVITY_ERR_10009.getCode());
			}
			//分享可以多次获得奖券
			if(!ActivityItemWayEnum.DISNEY_SHARE.getGiveCode().equals(lotteryGiveTypeStr.trim())){
				if(ActivityItemWayEnum.DISNEY_DRAW.getGiveCode().equals(lotteryGiveTypeStr.trim())){
					List<ActivityTakeRecord> alreadyTakeRecords = activityTakeRecordMapper.findActivityTakeRecordByParams(ToolsUtil.builderMapParams("activityId", Long.valueOf(activityIdStr), 
							"activityItemIds", activityItemIds, 
							"mobile", mobile));
					
					if(CollectionUtils.isNotEmpty(alreadyTakeRecords)){
						return new ActivityResultVo(false, ActivityErrorCodeEnum.ACTIVITY_ERR_10005.getMsg(), ActivityErrorCodeEnum.ACTIVITY_ERR_10005.getCode());
					}
				}else if(ActivityItemWayEnum.DISNEY_DRIVE_SIGNUP.getGiveCode().equals(lotteryGiveTypeStr.trim()) || ActivityItemWayEnum.DISNEY_DOWNLOAD_APP.getGiveCode().equals(lotteryGiveTypeStr.trim())){
					List<ActivityTakeRecord> alreadyTakeRecords = activityTakeRecordMapper.findActivityTakeRecordByParams(ToolsUtil.builderMapParams("activityItemIds", activityItemIds,  
							"mobile", mobile));
					if(CollectionUtils.isNotEmpty(alreadyTakeRecords)){
						return new ActivityResultVo(false, ActivityErrorCodeEnum.ACTIVITY_ERR_10005.getMsg(), ActivityErrorCodeEnum.ACTIVITY_ERR_10005.getCode());
					}
				}
			}
			
			ActivityItem activityItem = activityItems.get(0);
			List<String> unicodes = sequenceGenerator.getSequenceBatch(String.valueOf(activity.getActivityEachNum()), String.valueOf(activity.getActivityEachNum()), activityItem.getFaceValue());
			if(CollectionUtils.isNotEmpty(unicodes)){
				List<ActivityTakeRecord> takeRecords = new ArrayList<ActivityTakeRecord>();
				for(String unicode:unicodes){
					ActivityTakeRecord takeRecord = new ActivityTakeRecord();
					takeRecord.setActivityId(activity.getActivityId());
					takeRecord.setActivityCode(activity.getActivityCode());
					takeRecord.setActivityItemId(activityItem.getActivityItemId());
					takeRecord.setObtainUnicode(this.processUnicode(unicode, lottery_unicode_base, activity.getActivityEachNum()));
					takeRecord.setPrizeFlag(0);
					takeRecord.setObtainDate(new Date());
					if(StringUtils.isNotBlank(userId)){
						takeRecord.setUserId(userId);
					}
					if(StringUtils.isNotBlank(mobile)){
						takeRecord.setMobile(mobile);
					}
					if(StringUtils.isNotBlank(platform)){
						takeRecord.setPlatform(platform);
					}
					if(StringUtils.isNotBlank(deviceNo)){
						takeRecord.setDeviceNo(deviceNo);
					}
					takeRecords.add(takeRecord);
				}
				int effectRows = activityTakeRecordMapper.batchSave(takeRecords);
				if(effectRows > 0){
					Map<String, Object> dataMap = new HashMap<String, Object>();
					if(StringUtils.isNotBlank(userId)){
						dataMap.put("userId", userId);
					}
					if(StringUtils.isNotBlank(mobile)){
						dataMap.put("mobile", mobile);
					}
					//获得奖券数量
					dataMap.put("amount", unicodes.size());
					resultVo.setDataMap(dataMap);
					resultVo.setRet(true);
					resultVo.setErrmsg(ActivityErrorCodeEnum.ACTIVITY_ERR_10012.getMsg());
					return resultVo;
				}else{
					return new ActivityResultVo(false, ActivityErrorCodeEnum.ACTIVITY_ERR_10011.getMsg(), ActivityErrorCodeEnum.ACTIVITY_ERR_10011.getCode());
				}
			}else{
				return new ActivityResultVo(false, ActivityErrorCodeEnum.ACTIVITY_ERR_10010.getMsg(), ActivityErrorCodeEnum.ACTIVITY_ERR_10010.getCode());
			}
		}
	}
	
	/**
	 * Description: 校验送卷基本参数
	 * @Version1.0 2016年7月18日 下午4:32:23 by 代鹏（daipeng.456@gmail.com）创建
	 * @param userId
	 * @param mobile
	 * @param activityIdStr
	 * @param lotteryGiveTypeStr
	 * @return
	 */
	private ActivityResultVo checkActivityLotteryBaseParams(String userId, String mobile, String activityIdStr, String lotteryGiveTypeStr){
		if(StringUtils.isBlank(userId) && StringUtils.isBlank(mobile)){
			return new ActivityResultVo(false, ActivityErrorCodeEnum.ACTIVITY_ERR_10003.getMsg(), ActivityErrorCodeEnum.ACTIVITY_ERR_10003.getCode());
		}
		if(!ToolsUtil.checkParamsNotEmpty(activityIdStr, lotteryGiveTypeStr)){
			return new ActivityResultVo(false, ActivityErrorCodeEnum.ACTIVITY_ERR_10000.getMsg(), ActivityErrorCodeEnum.ACTIVITY_ERR_10000.getCode());
		}
		if(StringUtils.isNotBlank(mobile) && !ToolsUtil.checkMobilePhone(mobile)){
			return new ActivityResultVo(false, ActivityErrorCodeEnum.ACTIVITY_ERR_10013.getMsg(), ActivityErrorCodeEnum.ACTIVITY_ERR_10013.getCode());
		}
		ActivityItemWayEnum lotteryGiveType = ActivityItemWayEnum.getLotteryGiveEnumByCode(lotteryGiveTypeStr);
		if(null == lotteryGiveType){
			return new ActivityResultVo(false, ActivityErrorCodeEnum.ACTIVITY_ERR_10004.getMsg(), ActivityErrorCodeEnum.ACTIVITY_ERR_10004.getCode());
		}
		return new ActivityResultVo(true);
	}
	
	/**
	 * Description: 校验活动时效性
	 * @Version1.0 2016年7月18日 下午4:42:23 by 代鹏（daipeng.456@gmail.com）创建
	 * @param activity
	 * @return
	 */
	private ActivityResultVo checkActivityDateValid(Activity activity, ActivityResultVo resultVo){
		Date now = new Date();
		Date startDate = activity.getActivityStartDate();
		if(now.before(startDate) || ActivityPlayStatusEnum.UNSTART.getStatus() == activity.getActivityStatus()){
			return new ActivityResultVo(false, ActivityErrorCodeEnum.ACTIVITY_ERR_10006.getMsg(), ActivityErrorCodeEnum.ACTIVITY_ERR_10006.getCode());
		}
		
		Date endDate = activity.getActivityEndDate();
		if(endDate.before(now) || ActivityPlayStatusEnum.END.getStatus() == activity.getActivityStatus()){
			return new ActivityResultVo(false, ActivityErrorCodeEnum.ACTIVITY_ERR_10007.getMsg(), ActivityErrorCodeEnum.ACTIVITY_ERR_10007.getCode());
		}
		
		Date publishStartDate = activity.getActivityPublishStartDate();
		if(now.before(publishStartDate)){
			return new ActivityResultVo(false, ActivityErrorCodeEnum.ACTIVITY_ERR_10008.getMsg(), ActivityErrorCodeEnum.ACTIVITY_ERR_10008.getCode());
		}
		
		Date publishEndDate = activity.getActivityPublishEndDate();
		if(publishEndDate.before(now) || ActivityPlayStatusEnum.END.getStatus() == activity.getActivityStatus()){
			return new ActivityResultVo(false, ActivityErrorCodeEnum.ACTIVITY_ERR_10007.getMsg(), ActivityErrorCodeEnum.ACTIVITY_ERR_10007.getCode());
		}
		return resultVo;
	}
	
	/**
	 * Description: 按照基数处理卷码长度
	 * @Version1.0 2016年7月13日 下午3:30:39 by 代鹏（daipeng.456@gmail.com）创建
	 * @param code
	 * @param base
	 * @param num
	 * @return
	 */
	private String processUnicode(String code, long base, int num){
		if(StringUtils.isNotBlank(code)){
			long a = Long.valueOf(code);
			long c = a % base;
			return String.valueOf(num * base + c);
		}
		return null;
	}
	
}

package com.wxj.config;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class ScheduledTask {

//    @Autowired
//    DataMapper dataMapper;
//
//    @Autowired
//    SiteMapper siteMapper;
//
//    @Autowired
//    PressureMapper pressureMapper;
//
//    @Autowired
//    RedisUtil redisUtil;

    private final Logger logger = Logger.getLogger(getClass());

//    @Scheduled(fixedRate = 24 * 60 * 60 * 1000) //每天执行一次
//    public void addDateForm() {
//
//        List<String> siteIds = siteMapper.getEnableSiteIds();
//        Calendar calendar = Calendar.getInstance();
//        for (String siteId : siteIds) {
//            Date startDate;
//            //获取日报表中最近的一条数据的时间
//            startDate = dataMapper.getLastSiteDateFormTime(siteId);
//            if (ValidateUtil.isNullOrEmpty(startDate)) {
//                calendar.setTime(new Date());
//                calendar.set(Calendar.HOUR_OF_DAY, 0);
//                calendar.set(Calendar.MINUTE, 0);
//                calendar.set(Calendar.SECOND, 0);
//                calendar.set(Calendar.MILLISECOND, 0);
//                startDate = calendar.getTime();
//            }
//            calendar = Calendar.getInstance();
//            calendar.setTime(startDate);
//            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
//            Date endDate = calendar.getTime();
//            Double flow_total = dataMapper.getSiteFlowTotal("ll_datahistory" + siteId, startDate, endDate);
//            if (ValidateUtil.isNullOrEmpty(flow_total)){
//                continue;
//            }
//            Double flow_total_last = dataMapper.getLastSiteFlowTotal("ll_datahistory" + siteId, startDate);
//            SiteInfoBean siteInfo = redisUtil.getSiteInfo(siteId);
//            DateHourForm dateHourForm = new DateHourForm();
//            dateHourForm.setSite_name(siteInfo.getSite_name());
//            dateHourForm.setSite_id(siteId);
//            dateHourForm.setCreate_time(endDate);
//            dateHourForm.setFlow_total(flow_total);
//            dateHourForm.setFlow_total_last(flow_total_last);
//            dateHourForm.setFlow_instant(flow_total-flow_total_last);
//            dateHourForm.setWater_type(siteInfo.getWater_type());
//            dataMapper.addDateForm(dateHourForm);
//        }
//    }
//
//    @Scheduled(fixedRate = 60 * 60 * 1000) //每小时执行一次
//    public void addHourForm() {
//        List<String> siteIds = siteMapper.getEnableSiteIds();
//        Calendar calendar = Calendar.getInstance();
//        for (String siteId : siteIds) {
//            Date startDate;
//            //获取日报表中最近的一条数据的时间
//            startDate = dataMapper.getLastSiteHourFormTime(siteId);
//            if (ValidateUtil.isNullOrEmpty(startDate)) {
//                calendar.setTime(new Date());
//                calendar.set(Calendar.MINUTE, 0);
//                calendar.set(Calendar.SECOND, 0);
//                calendar.set(Calendar.MILLISECOND, 0);
//                startDate = calendar.getTime();
//            }
//            calendar = Calendar.getInstance();
//            calendar.setTime(startDate);
//            calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 1);
//            Date endDate = calendar.getTime();
//            Double flow_total = dataMapper.getSiteFlowTotal("ll_datahistory" + siteId, startDate, endDate);
//            if (ValidateUtil.isNullOrEmpty(flow_total)){
//                continue;
//            }
//            Double flow_total_last = dataMapper.getLastSiteFlowTotal("ll_datahistory" + siteId, startDate);
//            SiteInfoBean siteInfo = redisUtil.getSiteInfo(siteId);
//            DateHourForm dateHourForm = new DateHourForm();
//            dateHourForm.setSite_name(siteInfo.getSite_name());
//            dateHourForm.setSite_id(siteId);
//            dateHourForm.setCreate_time(endDate);
//            dateHourForm.setFlow_total(flow_total);
//            dateHourForm.setFlow_total_last(flow_total_last);
//            dateHourForm.setFlow_instant(flow_total-flow_total_last);
//            dateHourForm.setWater_type(siteInfo.getWater_type());
//            dataMapper.addHourForm(dateHourForm);
//        }
//    }


//    @Scheduled(fixedRate = 3*60*1000)
//    public void addPressureData(){
//        List<String> deviceIds=pressureMapper.getPressureDevices();
//        for (String deviceId:deviceIds){
//            Integer pressure=(int)(Math.random()*100+1);
//            pressureMapper.addPressureData(deviceId,pressure);
//            Integer id=pressureMapper.getRealTimeDataId(deviceId);
//            if (ValidateUtil.isNullOrEmpty(id)){
//                pressureMapper.addPressureRealData(deviceId,pressure);
//            } else {
//                pressureMapper.updatePressureRealData(id,pressure);
//            }
//        }
//    }
}

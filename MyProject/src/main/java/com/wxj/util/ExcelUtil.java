package com.wxj.util;

import org.springframework.stereotype.Component;

/**
 * Created by xushilong on 2017/11/23.
 */

@Component
public class ExcelUtil {

//    @Autowired
//    OssUtil ossUtil;
//
//    private final Logger logger = Logger.getLogger(getClass());
//
//    private HSSFFont title_font;
//    private HSSFFont param_font;
//    private HSSFFont content_font;
//    private HSSFCellStyle title_style;
//    private HSSFCellStyle param_style;
//    private HSSFCellStyle content_style;
//
//    public String exportPressureData(PressureDeviceBean deviceBean, List<PressureHistoryData> list) {
//        HSSFWorkbook wb = new HSSFWorkbook();
//        initExcelStyle(wb);
//        HSSFSheet sheet = wb.createSheet("压力数据表");
//        sheet.setColumnWidth(0, 10 * 512);
//        sheet.setColumnWidth(1, 12 * 512);
//
//        HSSFRow companyRow = sheet.createRow(0);
//        HSSFCell companyTitleCell = companyRow.createCell(0);
//        companyTitleCell.setCellValue("公司名:");
//        HSSFCell companyNameCell = companyRow.createCell(1);
//        companyNameCell.setCellValue(deviceBean.getCompany_name());
//
//        HSSFRow deviceRow = sheet.createRow(1);
//        HSSFCell deviceTitleCell = deviceRow.createCell(0);
//        deviceTitleCell.setCellValue("设备序列号:");
//        HSSFCell deviceIdCell = deviceRow.createCell(1);
//        deviceIdCell.setCellValue(deviceBean.getDevice_id());
//
//        HSSFRow modelRow = sheet.createRow(2);
//        HSSFCell modelTitleCell = modelRow.createCell(0);
//        modelTitleCell.setCellValue("类型名称:");
//        HSSFCell modelNameCell = modelRow.createCell(1);
//        modelNameCell.setCellValue(deviceBean.getModel_name());
//
//        String upperLimit = "未设置";
//        String lowerLimit = "未设置";
//        if (!ValidateUtil.isNullOrEmpty(deviceBean.getLimits())) {
//            for (PressureLimitBean limit : deviceBean.getLimits()) {
//                if (limit.getRelation().equals(">=")) {
//                    upperLimit = String.valueOf(roundByScale(limit.getPressure_limit(),3))+"kpa";
//                }
//                if (limit.getRelation().equals("<=")) {
//                    lowerLimit = String.valueOf(roundByScale(limit.getPressure_limit(),3))+"kpa";
//                }
//            }
//
//        }
//        HSSFRow upperLimitRow = sheet.createRow(3);
//        HSSFCell upperTitleCell = upperLimitRow.createCell(0);
//        upperTitleCell.setCellValue("压力上限:");
//        HSSFCell upperLimitCell = upperLimitRow.createCell(1);
//        upperLimitCell.setCellValue(upperLimit);
//
//        HSSFRow lowLimitRow = sheet.createRow(4);
//        HSSFCell lowerTitleCell = lowLimitRow.createCell(0);
//        lowerTitleCell.setCellValue("压力下限:");
//        HSSFCell lowerLimitCell = lowLimitRow.createCell(1);
//        lowerLimitCell.setCellValue(lowerLimit);
//
//        HSSFRow pressureDataTitle = sheet.createRow(5);
//        HSSFCell pressureTimeTitle = pressureDataTitle.createCell(0);
//        pressureTimeTitle.setCellValue("时间");
//        HSSFCell pressureValueTitle = pressureDataTitle.createCell(1);
//        pressureValueTitle.setCellValue("压力值(kpa)");
//
//        for (int i = 0; i < list.size(); i++) {
//            HSSFRow row = sheet.createRow(6 + i);
//            HSSFCell pressureTime = row.createCell(0);
//            HSSFCell pressureValue = row.createCell(1);
//            pressureTime.setCellValue(list.get(i).getAt().toLocaleString());
//            pressureValue.setCellValue(roundByScale(list.get(i).getPressure(), 3));
//        }
//        ByteArrayOutputStream fileOutputStream = new ByteArrayOutputStream();
//        ByteArrayInputStream inputStream = null;
//        String url = "";
//        try {
//            wb.write(fileOutputStream);
//            String fileName = "压力报表_" + System.currentTimeMillis() + ".xls";
//            if (fileName.length() > 150) {
//                fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
//            }
//            inputStream = new ByteArrayInputStream(fileOutputStream.toByteArray());
//            url = ossUtil.uploadFile(inputStream, "wateraffair/" + fileName);
//        } catch (IOException e) {
//            logger.error(e);
//        } finally {
//            try {
//                wb.close();
//                fileOutputStream.close();
//                inputStream.close();
//            } catch (IOException e) {
//                logger.error(e);
//            }
//        }
//        return url;
//    }
//
//    public String roundByScale(Double v, int scale) {
//        if (scale < 0) {
//            throw new IllegalArgumentException(
//                    "The   scale   must   be   a   positive   integer   or   zero");
//        }
//        if (scale == 0) {
//            return new DecimalFormat("0").format(v);
//        }
//        String formatStr = "0.";
//        for (int i = 0; i < scale; i++) {
//            formatStr = formatStr + "0";
//        }
//        return new DecimalFormat(formatStr).format(v);
//    }
//
//    public String exportFlowData(List<SiteCensusDetail> siteCensusDetails,Long areaId,Long siteId) {
//        HSSFWorkbook wb = new HSSFWorkbook();
//        initExcelStyle(wb);
//        HSSFSheet sheet = wb.createSheet("流量报表");
//
//        HSSFRow company_row = sheet.createRow(0);
//        HSSFCell company_id_cell = company_row.createCell(0);
//        company_id_cell.setCellValue("站点ID");
//        HSSFCell company_name_cell = company_row.createCell(1);
//        company_name_cell.setCellValue("站名");
//        HSSFCell company_time_cell = company_row.createCell(2);
//        company_time_cell.setCellValue("时间");
//        HSSFCell company_now_cell = company_row.createCell(3);
//        company_now_cell.setCellValue("本期流量(t)");
//        HSSFCell company_total_cell = company_row.createCell(4);
//        company_total_cell.setCellValue("当前总流量(t)");
//        HSSFCell company_last_cell = company_row.createCell(5);
//        company_last_cell.setCellValue("上期总流量");
//        for (int i = 0; i < siteCensusDetails.size(); i++) {
//            HSSFRow row = sheet.createRow(1 + i);
//            HSSFCell id = row.createCell(0);
//            HSSFCell name = row.createCell(1);
//            HSSFCell time = row.createCell(2);
//            HSSFCell now_flow = row.createCell(3);
//            HSSFCell total_flow = row.createCell(4);
//            HSSFCell last_flow = row.createCell(5);
////            if (i==0){
////                id.setCellValue(siteCensusDetails.get(i).getId());
////                name.setCellValue(siteCensusDetails.get(i).getName());
////                time.setCellValue(siteCensusDetails.get(i).getExport_time());
////                now_flow.setCellValue(ValidateUtil.isNullOrEmpty(siteCensusDetails.get(i).getFlow_now())?0D:siteCensusDetails.get(i).getFlow_now());
////                total_flow.setCellValue(siteCensusDetails.get(i).getFlow_total());
////                last_flow.setCellValue(siteCensusDetails.get(i).getFlow_last());
////            } else {
//            if (!ValidateUtil.isNullOrEmpty(siteCensusDetails.get(i).getId())) {
//                id.setCellValue(siteCensusDetails.get(i).getId());
//            }
//            name.setCellValue(siteCensusDetails.get(i).getName());
//            time.setCellValue(siteCensusDetails.get(i).getExport_time());
//            now_flow.setCellValue(roundByScale(siteCensusDetails.get(i).getFlow_now(), 3));
//            total_flow.setCellValue(roundByScale(siteCensusDetails.get(i).getFlow_total(), 3));
//            if ((i==siteCensusDetails.size()-1) && (ValidateUtil.isNullOrEmpty(areaId) && (ValidateUtil.isNullOrEmpty(siteId)))){
//                last_flow.setCellValue(new DecimalFormat("0.00%").format(Double.parseDouble(roundByScale(siteCensusDetails.get(i).getFlow_last(), 3))));
//            } else {
//                last_flow.setCellValue(roundByScale(siteCensusDetails.get(i).getFlow_last(), 3));
//            }
//            //}
//        }
//        ByteArrayOutputStream fileOutputStream = new ByteArrayOutputStream();
//        ByteArrayInputStream inputStream = null;
//        String url = "";
//        try {
//            wb.write(fileOutputStream);
//            String fileName = "流量报表_" + System.currentTimeMillis() + ".xls";
//            if (fileName.length() > 150) {
//                fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
//            }
//            inputStream = new ByteArrayInputStream(fileOutputStream.toByteArray());
//            url = ossUtil.uploadFile(inputStream, "wateraffair/" + fileName);
//        } catch (IOException e) {
//            logger.error(e);
//        } finally {
//            try {
//                wb.close();
//                fileOutputStream.close();
//                inputStream.close();
//            } catch (IOException e) {
//                logger.error(e);
//            }
//        }
//        return url;
//    }
//
//    public void initExcelStyle(HSSFWorkbook wb) {
//        // 创建字体样式
//        title_font = wb.createFont();
//        title_font.setFontName("宋体");
//        title_font.setBoldweight((short) 700);
//        title_font.setFontHeightInPoints((short) 16);// 设置字体大小
//
//        // 创建单元格样式
//        title_style = wb.createCellStyle();
//        title_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        title_style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        title_style.setFillPattern(HSSFCellStyle.NO_FILL);
//
//        title_style.setFont(title_font);// 设置字体F
//
//        // 创建字体样式
//        param_font = wb.createFont();
//        param_font.setFontName("宋体");
//        param_font.setBoldweight((short) 700);
//        param_font.setFontHeightInPoints((short) 12);// 设置字体大小
//
//        param_style = wb.createCellStyle();
//        param_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        param_style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        param_style.setFillPattern(HSSFCellStyle.NO_FILL);
//
//        param_style.setFont(param_font);// 设置字体F
//
//        // 创建字体样式
//        content_font = wb.createFont();
//        content_font.setFontName("宋体");
//        content_font.setFontHeightInPoints((short) 10);// 设置字体大小
//
//        content_style = wb.createCellStyle();
//        content_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        content_style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        content_style.setFillPattern(HSSFCellStyle.NO_FILL);
//
//        content_style.setFont(content_font);// 设置字体F
//    }
}

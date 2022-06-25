package com.wwt.example.indepartment.poi;

/**
 * @author wwt
 * @title: Object2Table
 * @description:
 * 只要将对象或者列表传进去，构造TableResultUtil对象
 * 就可以将结果处理为excel表格和html表格
 * @date 2022/6/25 13:53
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class Object2Table {

    public static void main(String[] args) throws IOException {
        template();
    }

    /**
     * 模板模式
     **/
    public static void template() throws IOException {

        OrderDTO orderDTO = new OrderDTO("z234","结束");
        TableTemplateImpl tableTemplate = new TableTemplateImpl();
        tableTemplate.setTableHead("单号","结果");
        TableResult4Template<TableTemplateImpl> tableResult =
                TableResult4Template.builder()
                        .template(tableTemplate)
                        .items(orderDTO.getOrderCode(),orderDTO.getStatus())
                        .build();


        String toolResult = tableResult.toJson();

        // 表格头及数据
        StringBuffer sBuffer = new StringBuffer();
        List<String> tableHead = dealTableHead2(toolResult);
        dealTableData2(toolResult,sBuffer);

        // 导出成excel
        String filePath = "D:\\模板设计模式" + System.currentTimeMillis()+".xls";
        export(tableHead,sBuffer,filePath);

        // 导出成html table
        String htmltable = convertTaskResultToHtmlTable(tableHead,sBuffer);
        System.out.println(htmltable);
    }

    /**
     * 建造者模式
     **/
    public static void build() throws IOException {
        //对象列表，支持泛型，单个对象和list都可以传
        List<OrderDTO> list = Lists.newArrayList();
        list.add(new OrderDTO("12345","创建中"));
        OrderDTO orderDTO = new OrderDTO("12346", "销毁");
        // 不传表格表头名为字段名，字段别名map可以将字段名替换成自定义的名称
        HashMap<String,String> tableHeadDisplayMap = new HashMap();
        tableHeadDisplayMap.put("orderCode","单号");

        // 建造json对象
        TableResultUtil<OrderDTO> tableResultUtil = new TableResultUtil<OrderDTO>();
        TableResult4Build<OrderDTO> tableResult=TableResult4Build.builder().tableHead(tableHeadDisplayMap)
                .item(orderDTO)
                .items(list).build();
        String jsonData = tableResultUtil.createTableResult(tableResult);

        // 根据对象生成json数据
        String toolResult = jsonData;

        // 表格头及数据
        StringBuffer sBuffer = new StringBuffer();
        List<String> tableHead = dealTableHead(toolResult);
        dealTableData(toolResult,sBuffer);

        // 导出成excel
        String filePath = "D:\\建造者设计模式" + System.currentTimeMillis()+".xls";
        export(tableHead,sBuffer,filePath);

        // 导出成html table
        String htmltable = convertTaskResultToHtmlTable(tableHead,sBuffer);
        System.out.println(htmltable);
    }

    /**
     * 导出服务
     * @return
     */
    private static HSSFWorkbook export(List<String> tableHeadDisPlayFiels,StringBuffer sBuffer, String filePath) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("result000");
        HSSFRow hssfRow = hssfSheet.createRow(0);
        HSSFCellStyle hssfCellStyle = hssfWorkbook.createCellStyle();
        HSSFDataFormat format = hssfWorkbook.createDataFormat();
        hssfCellStyle.setDataFormat(format.getFormat("@"));


        //填入表头
        int i = 0;
        for (String entry : tableHeadDisPlayFiels) {
            HSSFCell hssfCell = hssfRow.createCell(i);
            hssfCell.setCellValue(entry);
            hssfCell.setCellStyle(hssfCellStyle);
            i++;
        }

        //填入表内数据
        //第一行是表头，第二行开始填写数据
        List<String> resultList = Splitter.on("\n").trimResults().omitEmptyStrings().splitToList(sBuffer);
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(resultList)){
            for (int j = 0; j < resultList.size(); j++) {
                hssfRow = hssfSheet.createRow(j+1);
                List<String> fieldList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(resultList.get(j));
                if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(fieldList)) {
                    for (int k = 0; k < fieldList.size(); k++) {
                        HSSFCell hssfCell = hssfRow.createCell(k);
                        hssfCell.setCellStyle(hssfCellStyle);
                        hssfCell.setCellValue(String.valueOf(fieldList.get(k)));
                    }
                }
            }
        }
        FileOutputStream out = new FileOutputStream(filePath);
        //保存Excel文件
        hssfWorkbook.write(out);
        //关闭文件流
        out.close();
        return hssfWorkbook;

    }

    public static StringBuffer arrayIdToString2(List<String> tableHead, StringBuffer sBuffer) {
        for (String field: tableHead) {
            sBuffer.append(field).append(",");
        }
        // 这里写全局变量，和框架内容保持对齐
        return sBuffer.append("\n");
    }

    public static StringBuffer arrayIdToString(JSONObject jsonobejct, List<String> tableHead,
                                               StringBuffer sBuffer) {
        for (String field: tableHead) {
            sBuffer.append(jsonobejct.get(field)).append(",");
        }
        // 这里写全局变量，和框架内容保持对齐
        return sBuffer.append("\n");
    }

    public static List<String> dealTableHead(String toolResult) {
        JSONObject tableResultJson = JSONObject.parseObject(toolResult);
        JSONArray tableHeadDisplayArr = tableResultJson.getJSONArray("tableHeadDisplay");
        List<String> tableHeadDisPlayFiels = tableHeadDisplayArr.toJavaList(String.class);
        return tableHeadDisPlayFiels;
    }
    public static List<String> dealTableHead2(String toolResult) {
        JSONObject tableResultJson = JSONObject.parseObject(toolResult);
        JSONArray tableHeadArr = tableResultJson.<String>getJSONArray("tableHead");
        tableHeadArr.toJavaList(String.class);
        List<String> tableHeadDisPlayFiels = tableHeadArr.toJavaList(String.class);
        return tableHeadDisPlayFiels;
    }

    public static void dealTableData(String toolResult, StringBuffer sBuffer){
        JSONObject tableResultJson = JSONObject.parseObject(toolResult);
        JSONArray tableData = tableResultJson.getJSONArray("tableData");
        JSONArray tableHeadArr = tableResultJson.getJSONArray("tableHead");
        List<String> tableHeadFiels = tableHeadArr.toJavaList(String.class);
        tableData.stream().forEach(jsonobejct -> arrayIdToString((JSONObject) jsonobejct, tableHeadFiels, sBuffer));
    }

    public static void dealTableData2(String toolResult, StringBuffer sBuffer){
        JSONObject tableResultJson = JSONObject.parseObject(toolResult);
        JSONArray tableDataArr = tableResultJson.getJSONArray("tableData");
        List<String> tableDataFiels = tableDataArr.toJavaList(String.class);
        arrayIdToString2( tableDataFiels, sBuffer);
    }

    public static String convertTaskResultToHtmlTable(List<String> tableHeadDisPlayFiels,StringBuffer sBuffer){


        StringBuffer sb = new StringBuffer();
        sb.append("<table border='1' cellspacing='0' cellpadding='3'  align='center'><tr>");
        // 获取显示字段名称，并转换成html格式
        if (CollectionUtils.isNotEmpty(tableHeadDisPlayFiels)) {
            tableHeadDisPlayFiels.forEach(tableHeadFiel -> {
                sb.append("<th>");
                sb.append(tableHeadFiel);
                sb.append("</th>");
            });
        }
        sb.append("</tr>");

        // 转换值为table格式
        List<String> resultList = Splitter.on("\n").trimResults().omitEmptyStrings()
                .splitToList(sBuffer);

        if (CollectionUtils.isNotEmpty(resultList)) {
            resultList.forEach(r -> {
                sb.append("<tr>");
                List<String> fieldList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(r);
                if (CollectionUtils.isNotEmpty(fieldList)) {
                    fieldList.forEach(field -> {
                        sb.append("<td>");
                        sb.append(field);
                        sb.append("</td>");
                    });
                }
                sb.append("</tr>");
            });
        }
        sb.append("</table>");
        return sb.toString();
    }
}

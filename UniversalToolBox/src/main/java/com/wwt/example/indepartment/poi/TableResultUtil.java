package com.wwt.example.indepartment.poi;

/**
 * @author wwt
 * @title: TableResultUtil
 * @description: TODO
 * @date 2022/6/25 14:10
 */

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class TableResultUtil<E> {

    public String createTableResult(TableResult4Build<E> tableResult) {
        Map<String, String> tableHeadMap = tableResult.getTableHead();
        List<E> list = tableResult.getTableRows();

        JSONObject resultObjWhole = new JSONObject();
        List<String> tableHead = createTableHead(list);
        List<String> tableHeadDisplay = Lists.newArrayList();
        tableHeadDisplay.addAll(tableHead);
        if (!org.springframework.util.CollectionUtils.isEmpty(tableHeadMap)) {
            for (Map.Entry<String, String> map : tableHeadMap.entrySet()) {
                for (int i = 0; i < tableHeadDisplay.size(); i++) {
                    if (map.getKey().equals(tableHeadDisplay.get(i))) {
                        tableHeadDisplay.set(i, map.getValue());
                    }
                }

            }
        }
        if (org.springframework.util.CollectionUtils.isEmpty(list)) {
            throw new RuntimeException("表格结果构建结果为空");
        }

        resultObjWhole.put("tableHead", tableHead);
        resultObjWhole.put("tableHeadDisplay", tableHeadDisplay);
        resultObjWhole.put("tableData", list);
        return resultObjWhole.toJSONString();
    }

    /**
     * 对象所有字段名
     *
     * @return
     * @paramlist
     */
    private List<String> createTableHead(List<E> list) {
        List<String> tableHead = Lists.newArrayList();
        if (list == null || list.size() < 1) {
            throw new RuntimeException("对象tableHead参数构建失败，入参为空");
        }
        // 构造表头和数据
        Field[] fields = list.get(0).getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            tableHead.add(fields[i].getName());
        }
        return tableHead;
    }
}
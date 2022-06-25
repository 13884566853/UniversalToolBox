package com.wwt.example.indepartment.poi;

/**
 * @author wwt
 * @title: TableTemplateImpl
 * @description: TODO
 * @date 2022/6/25 14:22
 */

import com.google.common.collect.Lists;

import java.util.List;

public class TableTemplateImpl implements TableTemplate {

    private List<String> headList;

    public List<String> getHeadList() {
        return headList;
    }


    @Override
    public void setTableHead(String... tableHead) {
        headList = Lists.newArrayList(tableHead);
    }

    @Override
    public List<String> getTableHead() {
        return this.headList;
    }


    @Override
    public Object setTableStyle() {
        return null;
    }
}
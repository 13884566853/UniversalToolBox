package com.wwt.example.indepartment.poi;

import java.util.List;

/**
 * @author wwt
 * @title: dsgd
 * @description: TODO
 * @date 2022/6/25 14:18
 */
interface TableTemplate<T extends TableTemplate> {
    void setTableHead(String... tableHead);
    List<String> getTableHead();
    abstract Object setTableStyle();
}
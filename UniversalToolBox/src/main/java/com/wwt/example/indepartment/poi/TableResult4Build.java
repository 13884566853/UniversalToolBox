package com.wwt.example.indepartment.poi;

/**
 * @author wwt
 * @title: TableResult
 * @description: TODO
 * @date 2022/6/25 14:20
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableResult4Build<T> {
    private List<T> tableRows;
    private Map<String, String> tableHead;

    public List<T> getTableRows() {
        return tableRows;
    }

    public Map<String, String> getTableHead() {
        return tableHead;
    }

    public TableResult4Build(Map<String, String> tableHead, List<T> tableRows) {
        this.tableHead = tableHead;
        this.tableRows = tableRows;
    }

    @Override
    public String toString() {
        return "TableResult{" +
                "tableRows=" + tableRows +
                ", tableHead=" + tableHead +
                '}';
    }

    public static TableResult4Build.TableResultBuilder builder() {
        return new TableResult4Build.TableResultBuilder();
    }

     static class TableResultBuilder<T> {

        private List<T> tableRows = new ArrayList<>();
        private Map<String, String> tableHead;
        private T item;
        private Map<String, String> tableHeadMap;

        TableResultBuilder() {
        }

        public TableResult4Build.TableResultBuilder item(T t) {
            this.tableRows.add(t);
            return this;
        }

        public TableResult4Build.TableResultBuilder items(List<T> items) {
            this.tableRows.addAll(items);
            return this;
        }

        public TableResult4Build.TableResultBuilder tableHead(Map<String, String> tableHeadMap) {
            this.tableHead = tableHeadMap;
            return this;
        }

        public TableResult4Build build() {
            return new TableResult4Build(this.tableHead, this.tableRows);
        }
    }
}
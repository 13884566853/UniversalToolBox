package com.wwt.example.indepartment.poi;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.List;

public class TableResult4Template<T extends TableTemplate> {
    private T template;
    private List<String> bodyList;

    public T getTemplate() {
        return template;
    }

    public void setTemplate(T template) {
        this.template = template;
    }

    public void setBodyList(List<String> bodyList) {
        this.bodyList = bodyList;
    }

    public List<String> getBodyList() {
        return bodyList;
    }

    private TableResult4Template() {
    }

    public String toJson(){
        if (this.getTemplate() == null||this.getBodyList()==null) {
            throw new RuntimeException("模板内参数不允许为空");
        }

        if (this.getTemplate().getTableHead().size() != this.getBodyList().size()) {
            throw new RuntimeException("表头与内容参数长度不一致");
        }
        JSONObject resultObjWhole = new JSONObject();

        resultObjWhole.put("tableHead",this.getTemplate().getTableHead());
        resultObjWhole.put("tableData",this.getBodyList());
        return resultObjWhole.toJSONString();
    }


    public static TableResult4Template.TableResultBuilder builder() {
        return new TableResult4Template.TableResultBuilder();
    }

    public static class TableResultBuilder<T extends TableTemplate> {

        TableResult4Template tableResult2 = new TableResult4Template();
        private T template;
        private List<String> bodyList;


        TableResultBuilder() {
        }

        public TableResult4Template.TableResultBuilder template(T template) {
            this.template = template;
            return this;
        }

        public TableResult4Template.TableResultBuilder items(String... items) {
            this.bodyList = Lists.newArrayList(items);
            return this;
        }

        public TableResult4Template build() {
            tableResult2.setTemplate(this.template);
            tableResult2.setBodyList(this.bodyList);
            return tableResult2;
        }
    }
}

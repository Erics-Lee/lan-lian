package com.unionblue.wechat.model;

/**
 * Created by 18501 on 2018/10/25.
 */
public class Invoices {

    //id
    private String id;

    //企业名称
    private String companyno;

    //国家
    private String country;

    //作业编号
    private String taskid;

    //发票编号
    private String invoiceid;

    //发票税金
    private String invoiceTaxPrice;

    //币种
    private String countryCurrency;

    //消费类型
    private String consumptionType;

    //发票状态
    private String status;

    //上传日期
    private String createtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyno() {
        return companyno;
    }

    public void setCompanyno(String companyno) {
        this.companyno = companyno;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(String invoiceid) {
        this.invoiceid = invoiceid;
    }

    public String getInvoiceTaxPrice() {
        return invoiceTaxPrice;
    }

    public void setInvoiceTaxPrice(String invoiceTaxPrice) {
        this.invoiceTaxPrice = invoiceTaxPrice;
    }

    public String getCountryCurrency() {
        return countryCurrency;
    }

    public void setCountryCurrency(String countryCurrency) {
        this.countryCurrency = countryCurrency;
    }

    public String getConsumptionType() {
        return consumptionType;
    }

    public void setConsumptionType(String consumptionType) {
        this.consumptionType = consumptionType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime.replaceAll("T"," ");
    }
}

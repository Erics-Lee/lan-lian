package com.unionblue.wechat.service;

/**
 * Created by 18501 on 2018/9/19.
 */
public interface InvoiceService {

    String InvoicesTasksQueryE(String sessionKey);

    String InvoicesTasksQueryA(String sessionKey);

    String InvoicesTasksQueryB(String sessionKey, String status, String country);

    String invoiceQuery(String sessionKey, String tasksid, String invoiceid);

    String invoicePhoto(String sessionKey, String tasksid, String invoiceid);

    String getInvoiceTaskStatus();

	String uploadInvoiceImageFiles(String sessionKey, String base64Codes);

}
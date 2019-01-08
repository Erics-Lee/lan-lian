package com.unionblue.wechat.controller;

import com.unionblue.wechat.service.OrderService;
import com.unionblue.wechat.util.HttpClinetUtil;
import com.unionblue.wechat.util.JsonUtil;
import com.unionblue.wechat.wechatService.WeixinUrlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 18501 on 2018/8/31.
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    protected static final Logger logger = LoggerFactory.getLogger(WeixinUrlFilter.class);

    /**
     * 生成订单
     * @param request
     * @param response
     * @param InvoiceTasks 多笔发票数据
     * @return
     */
    @RequestMapping(value = "invoiceOrder", method = RequestMethod.POST)
    @ResponseBody
    public String invoiceOrder(HttpServletRequest request, HttpServletResponse response, String InvoiceTasks) {
        try {
            if (InvoiceTasks == null && InvoiceTasks.equals("")) {
                logger.info("发票列表不可为空");
            }
//            List<InvoiceTasks> list = JSON.parseArray(InvoiceTasks, InvoiceTasks.class);
            String sessionKey = HttpClinetUtil.getSessionKey(request);
            String result = orderService.invoiceOrder(sessionKey,InvoiceTasks,request);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.error("生成订单失败");
        }
    }

    /**
     * 订单数量
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "OrderNumber")
    @ResponseBody
    public String OrderNumber(HttpServletRequest request, HttpServletResponse response) {
        try {
            String sessionKey = HttpClinetUtil.getSessionKey(request);
            String result = orderService.orderNumber(sessionKey);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.error("订单列表查询失败");
        }
    }

    /**
     * 订单列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "invoiceOrderQuery")
    @ResponseBody
    public String invoiceOrderQuery(HttpServletRequest request, HttpServletResponse response) {
        try {
            String sessionKey = HttpClinetUtil.getSessionKey(request);
            String result = orderService.invoiceOrderQuery(sessionKey);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.error("订单列表查询失败");
        }
    }

    /**
     * 查询订单状态
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "getInvoiceOrderStatus")
    @ResponseBody
    public String getInvoiceOrderStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            String result = orderService.getInvoiceOrderStatus();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.error("订单状态查询失败");
        }
    }

}

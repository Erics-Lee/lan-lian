package com.unionblue.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.unionblue.wechat.model.UploadPicInfo;
import com.unionblue.wechat.service.InvoiceService;
import com.unionblue.wechat.util.HttpClinetUtil;
import com.unionblue.wechat.util.InvoiceUploadProgress;
import com.unionblue.wechat.util.JsonUtil;
import com.unionblue.wechat.util.StringUtil;
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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 18501 on 2018/8/31.
 */
@Controller
@RequestMapping(value = "/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    protected static final Logger logger = LoggerFactory.getLogger(WeixinUrlFilter.class);

    /**
     * 发票数量
     * @param request
     * @param response
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "InvoicesTasksQueryE")
    @ResponseBody
    public String InvoicesTasksQueryE(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        try {
            String sessionKey = HttpClinetUtil.getSessionKey(request);
            String result = invoiceService.InvoicesTasksQueryE(sessionKey);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.error("发票数量查询失败");
        }
    }

    /**
     * 发票列表
     * @param request
     * @param response
     * @param status 国家
     * @param country 国家
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "InvoicesTasksQueryB")
    @ResponseBody
    public String InvoicesTasksQueryB(HttpServletRequest request, HttpServletResponse response, String status, String country) throws ParseException {
        try {
            if (status == null && status.equals("")) {
                logger.info("发票状态不可为空");
            }
            String sessionKey = HttpClinetUtil.getSessionKey(request);
            String result = invoiceService.InvoicesTasksQueryB(sessionKey,status,country);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.error("发票列表查询失败");
        }
    }

    /**
     * 发票详情
     * @param request
     * @param response
     * @param taskid 发票作业id
     * @param invoiceid 发票id
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "invoiceQuery")
    @ResponseBody
        public String invoiceQuery(HttpServletRequest request, HttpServletResponse response, String taskid, String invoiceid) throws ParseException {
        try {
            if (taskid == null && taskid.equals("")) {
                logger.info("发票作业ID不可为空");
            }
            if (invoiceid == null && invoiceid.equals("")) {
                logger.info("发票ID不可为空");
            }
            String sessionKey = HttpClinetUtil.getSessionKey(request);
            String result = invoiceService.invoiceQuery(sessionKey,taskid,invoiceid);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.error("发票详情查询失败");
        }
    }

    /**
     * 查询发票状态
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "getInvoiceTaskStatus")
    @ResponseBody
    public String getInvoiceTaskStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            String result = invoiceService.getInvoiceTaskStatus();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.error("发票状态查询失败");
        }
    }
    
    /**
     * 获取上传发票状态
     * @return
     */
    @RequestMapping("/invoiceUploadProgress")
    @ResponseBody
	public String invoiceUploadProgress(HttpServletRequest request) {
    	String result = InvoiceUploadProgress.map.get(HttpClinetUtil.getSessionKey(request));
		return result;
	}
    
    /**
     * 上传发票
     * @param base64Codes 发票图片base码
     * @return
     */
	@RequestMapping(value = "/uploadInvoiceImageFiles", method = RequestMethod.POST)
    @ResponseBody
	public String uploadInvoiceImageFiles(HttpServletRequest request, String base64Codes) throws Exception{
		try {
			if(StringUtil.isEmpty(base64Codes)){
				InputStream inputStream;
				inputStream = request.getInputStream();
				
				//建立接收流缓冲，准备处理
				StringBuffer requestBuffer = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
				//读入流，并转换成字符串
				String readLine;
				while ((readLine = reader.readLine()) != null) {
					requestBuffer.append(readLine);
				}
				reader.close();
				System.out.println(1+"  "+java.net.URLDecoder.decode(requestBuffer.toString(), "utf-8").length());
				base64Codes = java.net.URLDecoder.decode(requestBuffer.toString(), "utf-8").substring(12);				
			}
            String sessionKey = HttpClinetUtil.getSessionKey(request);
			System.out.println(base64Codes.length());
            List<UploadPicInfo> list = JSON.parseArray(base64Codes, UploadPicInfo.class);
            System.out.println(list.size());
            List<UploadPicInfo> list2 = new ArrayList<UploadPicInfo>();
            Map<String, String> map = new HashMap<String, String>();
            map.put("ParentAppKey", "d32ddqwr2sqf4t3qef4t34fqwq32r2de2ed");
            map.put("companyno", list.get(0).getCompanyno());
            for(int i=0;i<list.size();i++){
            	map.put("ImageBinaryCodes", list.get(i).getImageBinaryCodes());
            	InvoiceUploadProgress.map.put(sessionKey, i+"/"+list.size());
            	String picInfo = "["+JSONObject.toJSONString(map)+"]";
            	String result = invoiceService.uploadInvoiceImageFiles(sessionKey,picInfo);
            	System.out.println(result);
            	if(!result.equals("200")){
            		UploadPicInfo info = new UploadPicInfo();
            		info.setCompanyno(list.get(0).getCompanyno());
            		info.setImageBinaryCodes(list.get(i).getImageBinaryCodes());
            		info.setErrMessage(result);
            		list2.add(info);
            	}
            }
            InvoiceUploadProgress.map.remove(HttpClinetUtil.getSessionKey(request));
            if(list2 != null && list2.size() > 0){
            	return JsonUtil.error(list2);
            }else{
            	return JsonUtil.success();        	
            }
        } catch(Exception e) {
            e.printStackTrace();
            return JsonUtil.error("数据加载异常");
        }
        
	}

}

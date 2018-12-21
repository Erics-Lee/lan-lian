package com.unionblue.wechat.controller;

import com.unionblue.wechat.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/country")
public class CountryController {

	//private static final Logger LOG = LoggerFactory.getLogger(CountryController.class);
	
	@Autowired
	private ICountryService service;
	
	/**
     * 查询退税国家
     * @return
     */
	@RequestMapping("/selectTaxRefundCountrys")
    @ResponseBody
    public String selectTaxRefundCountrys(HttpServletRequest request) {
		String result = service.selectTaxRefundCountrys();
		return result;
	}
	
	/**
     * 查询退税国家政策
     * @param countryNumber 国家编码ID
     * @return
     */
	@RequestMapping("/selectTaxRefundPolicy")
    @ResponseBody
	public String selectTaxRefundPolicy(HttpServletRequest request, String countryNumber) {
		String result = service.selectTaxRefundPolicy(countryNumber);
		return result;
	}

	/**
	 * 查询退税国家货币
	 * @return
	 */
	@RequestMapping("/InternationalCurrencys")
	@ResponseBody
	public String InternationalCurrencys(HttpServletRequest request) {
		String result = service.internationalCurrencys();
		return result;
	}

	/**
	 * 查询退税国家起退线
	 * @return
	 */
	@RequestMapping("/CountryTaxRefundAmounts")
	@ResponseBody
	public String CountryTaxRefundAmounts(HttpServletRequest request) {
		String result = service.countryTaxRefundAmounts();
		return result;
	}
}
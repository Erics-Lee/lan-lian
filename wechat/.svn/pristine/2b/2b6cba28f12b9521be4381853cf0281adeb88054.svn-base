package com.unionblue.wechat.service.impl;


import com.unionblue.wechat.service.ITestService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
public class TestServiceImpl implements ITestService{
	
	@Override
    @Cacheable(value = {"test"},key ="#info")
	public String testInfo(String info) {
		System.out.println(info);
		return info+"_1";
	}
	
	/*通过key值删除redis数据
	 * key值:info 
	 * */
	@Override
    @CacheEvict(value="#info")
	public String testDeleteInfo(String info) {
		return null;
	} 


}

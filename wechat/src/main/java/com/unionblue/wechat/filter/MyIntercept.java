package com.unionblue.wechat.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyIntercept extends WebMvcConfigurerAdapter{
	
	 @Override
	 public void addCorsMappings(CorsRegistry registry) {
	        System.out.println("我是MyWebConfig跨域");
	        //设置允许跨域的路径
	        registry.addMapping("/**")
	                //设置允许跨域请求的域名
	                .allowedOrigins("*")
	                //是否允许证书 不再默认开启
	                .allowCredentials(true)
	                //设置允许的方法
	                .allowedMethods("*")
	                //跨域允许时间
	                .maxAge(1800);
	    }


}

package com.example.demo.configmessagequeue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Component
public class TenantServiceInterceptorAppConfig  extends WebMvcConfigurerAdapter {
	   @Autowired
	   TenantServiceInterceptor tenantServiceInterceptor;

	   @Override
	   public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(tenantServiceInterceptor);
	   }
	}
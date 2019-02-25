package com.example.demo.configmessagequeue;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.configAuthen.JwtParser;
import com.example.demo.configAuthen.Sender;

@Component
public class TenantServiceInterceptor implements HandlerInterceptor{
	 @Override
	   public boolean preHandle(
	      HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		 
		 //authen
		  if(JwtParser.getSubject(request,response)==null) {
			  response.sendError(401,"UNAUTHORIZED REQUEST");	 
		      return false;
		  }
		  
		  // validate keycompany with userId
		  String keycompany=request.getHeader("keycompany");
		  Sender sender=(Sender)request.getAttribute("sender");
		  long userId=sender.getId();
		  if (!TenantInfo.validateKeycompany(keycompany,String.valueOf(userId)) ) {
			  response.sendError(401,"UNAUTHORIZED REQUEST");	 
		      return false;
		  };	
		  
		  //take keydatabase
		  String keydatabase=TenantInfo.getKeydatabase(keycompany);
		  request.setAttribute("keydatabase", keydatabase);
		  
		  
		  
		return true;

	   }
	   @Override
	   public void postHandle(
	      HttpServletRequest request, HttpServletResponse response, Object handler, 
	      ModelAndView modelAndView) throws Exception {}
	   
	   @Override
	   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
	      Object handler, Exception exception) throws Exception {}
}

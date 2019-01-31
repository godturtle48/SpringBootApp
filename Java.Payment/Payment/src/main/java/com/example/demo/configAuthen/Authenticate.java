package com.example.demo.configAuthen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Authenticate{
/*
 * Call if you want to authenticate a request
 *  If true ,skip and set sender info in req attribute 
 *	else throw UnauthorizeException then send error status code
 */
	
	public static void Auth(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if(JwtParser.getSubject(httpServletRequest)==null)
			throw new UnauthorizeException();
	}
}

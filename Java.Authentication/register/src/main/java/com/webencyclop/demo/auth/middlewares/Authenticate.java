package com.webencyclop.demo.auth.middlewares;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webencyclop.demo.auth.exception.UnauthorizeException;
import com.webencyclop.demo.auth.util.JwtUtil;

public class Authenticate{

	public static void Auth(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if(JwtUtil.getSubject(httpServletRequest)==null)
			throw new UnauthorizeException();
	}
}

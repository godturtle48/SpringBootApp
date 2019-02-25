package com.example.demo.configAuthen;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtParser {
//	@Value("${auth.signingKey}")
    private static String signingKey="privatekey";
    private static String tokenHeaderName="authorization";
    
	public static String getSubject(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader(tokenHeaderName);
        if(token == null) return null;
        try {            
        	Claims claims=Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
        	String email=claims.getSubject();
        	int id=(int) claims.get("id");
        	int roleId=(int) claims.get("roleId");    	
        	Sender sender=new Sender(id,email,roleId);
        	request.setAttribute("sender", sender);
        	
        	return claims.getSubject();
        }catch(Exception e){
        	return null;  
        }
		      
    }
    
}
package com.example.demo.configmessagequeue;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;

public class TenantInfo {
	static  Map<String, SessionFactory> sessionFactoryMap=new HashMap<>();
	static  Map<String, String> databaseMap=new HashMap<>();
	static  Map<String, String> userMap=new HashMap<>();
	
	public static SessionFactory getConnect(String keydatabase) {
		System.out.println(sessionFactoryMap.get(keydatabase));
		return sessionFactoryMap.get(keydatabase);
	}
	
	public static String getKeydatabase(String keycompany) {
		return databaseMap.get(keycompany);
	}
	
	public static boolean validateKeycompany(String keycompany,String userId) {
		return userId.equals(userMap.get(keycompany));		
	}
}

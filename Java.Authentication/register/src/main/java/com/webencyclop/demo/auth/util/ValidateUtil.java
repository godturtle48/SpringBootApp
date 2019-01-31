package com.webencyclop.demo.auth.util;

public class ValidateUtil {
    public static boolean isValidEmailAddress(String email) {
    	if(email==null) return false;
    	email=email.trim();
    	if (email.length()>100 || email.length()<4) return false;
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
    
    public static boolean isValidPassword(String password) {
    	if(password==null) return false;
    	if (password.length()>100 || password.length()<6) return false;
        return true;
    }
    
    
}

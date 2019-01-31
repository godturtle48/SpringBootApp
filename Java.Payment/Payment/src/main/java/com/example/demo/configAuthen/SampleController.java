package com.example.demo.configAuthen;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SampleController {
	@RequestMapping(value = "/api/sampleParser", method = RequestMethod.GET)
	public ResponseEntity<Object> sample(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {		
	    //  API need Authenticate
		Authenticate.Auth(httpServletRequest, httpServletResponse); 
		// get sender info if you need
		Sender sender= (Sender) httpServletRequest.getAttribute("sender");
		// do something 
		String result="id :" +sender.getId()
						+"    roleId:"+sender.getRoleId()
						+"   email:"+sender.getEmail();
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
}

package br.com.kingsdevs.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PasswordController {

	@RequestMapping(value = "/password-recover")
	public ModelAndView password() throws IOException{
	//public ModelAndView password(HttpServletResponse response) throws IOException{	
		
		/*WEB-INF/views/password.jsp*/
		ModelAndView modelAndView = new ModelAndView("password");
		
		return modelAndView;
		
	}

}

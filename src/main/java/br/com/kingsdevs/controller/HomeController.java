package br.com.kingsdevs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	// método para mapear a rota que irá abrir a página
	@RequestMapping(value = "/home")
	public ModelAndView home(HttpServletRequest request) {
	
		// definindo a página jsp que será aberta na rota /home
		// WEB-INF/views/agenda/home.jsp
		ModelAndView modelAndView = new ModelAndView("agenda/home");
		
		//verificar se não há um usuário autenticado em sessão
		if(request.getSession().getAttribute("usuario_autenticado") == null) {
		
			//redirecionar de volta para a página inicial do sistema
			modelAndView.setViewName("redirect:/");		
		}		
		
		// abrindo a página
		return modelAndView;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
		
		//apagar os dados gravados na sessão
		request.getSession().removeAttribute("usuario_autenticado");
		
		//redirecionar de volta para a página de login
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("redirect:/");
		
		return modelAndView;
		
	}
	
	
	
	
	

}

package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	// método para mapear a rota da página de login (raiz do projeto)
		@RequestMapping(value = "/home")
		public ModelAndView home(HttpServletRequest request) {

			// definir qual página jsp será aberta na rota /home
			// WEB-INF/views/agenda/home.jsp
			ModelAndView modelAndView = new ModelAndView("agenda/home");

			//se não houver um usuario autenticado na Sessao, o sistema joga o engraçado para a tela de logon
			if (request.getSession().getAttribute("usuario_autenticado") == null) {
				
				//redirecionar de volta para a página inicial do sistema
				modelAndView.setViewName("redirect:/");				
			}
			//abrindo a pagina login.jsp, que é a pagina mapeada no raiz "/"
			return modelAndView;
		}
		
		//método para mapear a rota de logout do sistema
		@RequestMapping(value = "/logout")
		public ModelAndView logout(HttpServletRequest request) {

			// apagar os dados gravados na sessão
			request.getSession().removeAttribute("usuario_autenticado");

			// redirecionar de volta para a página de login
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("redirect:/");
			return modelAndView;

		}
		
		
		
		
}


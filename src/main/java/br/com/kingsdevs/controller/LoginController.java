package br.com.kingsdevs.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.kingsdevs.entities.Usuario;
import br.com.kingsdevs.repositories.UsuarioRepository;

@Controller
public class LoginController {

	@RequestMapping(value = "/")
	public ModelAndView login() throws IOException {

		// WEB-INF/views/login.jsp
		ModelAndView modelAndView = new ModelAndView("login");

		return modelAndView;
	}

	/*HttpServletRequest -> Classe Java utilizada no Spring MVC para fazer a 
	 * captura dos campos enviados por um formulário ou uma querystring (URL).*/
	
	@RequestMapping(value = "/autenticar-usuario", method = RequestMethod.POST)
	public ModelAndView autenticarUsuario(HttpServletRequest request) {
		
		// WEB-INF/views/login.jsp
		ModelAndView modelAndView = new ModelAndView("login");
		
		try {
			// capturar os campos enviados pelo formulário
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			// consultar no banco de dados o usuário atraves do email e da senha
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			 
			Usuario usuario = usuarioRepository.obterPorEmailESenha(email, senha);
			
			// verificando se o usuário foi encontrado
			if (usuario != null) {
				
				//armazenar os dados do usuário em uma sessão
				//usuario_autenticado -> é o nome da sessao criada
				request.getSession().setAttribute("usuario_autenticado", usuario);
				
				//redirecionar para a página home do sistema
				modelAndView.setViewName("redirect:home");
				
			} else {
				
				throw new Exception("Acesso negado, email e senha inválidos.");
				
			}
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
		
	}

}

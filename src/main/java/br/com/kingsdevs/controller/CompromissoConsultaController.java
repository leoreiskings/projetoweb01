package br.com.kingsdevs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.kingsdevs.entities.Compromisso;
import br.com.kingsdevs.entities.Usuario;
import br.com.kingsdevs.repositories.CompromissoRepository;

@Controller
public class CompromissoConsultaController {

	@RequestMapping(value = "/consultar-compromissos")
	public ModelAndView consulta(HttpServletRequest request) // manipulando a sessao do usuario com HttpServletRequest 
	{
	
	// WEB-INF/views/agenda/consulta.jsp
	ModelAndView modelAndView = new ModelAndView("agenda/consulta");
	
	try {
		
			//capturando o usuário autenticado na sessão
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");
			
			CompromissoRepository compromissoRepository = new CompromissoRepository();
			
			List<Compromisso> lista = compromissoRepository.obterTodos(usuario.getIdUsuario());
			
			modelAndView.addObject("compromissos", lista); // modelAndView.addObject
														   //transporta para a pagina qq objeto
		}
	
			catch(Exception e) {
	
				modelAndView.addObject("mensagem_erro", e.getMessage());
		
		}
	
	return modelAndView;
	
	}
	
	
	
	
}
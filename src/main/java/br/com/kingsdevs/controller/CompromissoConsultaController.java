package br.com.kingsdevs.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		// ao carregar a pagina ele já entra e executa o metodod obtertodos e carrega na
		// tela todos os compromissos do usuario autenticado
		ModelAndView modelAndView = new ModelAndView("agenda/consulta");

		try {

			// capturando o usuário autenticado na sessão
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");

			CompromissoRepository compromissoRepository = new CompromissoRepository();

			List<Compromisso> lista = compromissoRepository.obterTodos(usuario.getIdUsuario());

			modelAndView.addObject("compromissos", lista); // modelAndView.addObject
															// transporta para a pagina qq objeto
		}

		catch (Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		return modelAndView;

	}

	@RequestMapping(value = "/pesquisar-compromissos", method = RequestMethod.POST)
	public ModelAndView pesquisarCompromissos(HttpServletRequest request) {

		// WEB-INF/views/agenda/consulta.jsp
		ModelAndView modelAndView = new ModelAndView("agenda/consulta");

		try {

			// capturar as datas enviadas pelo formulário
			Date dataMin = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataMin"));
			Date dataMax = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataMax"));

			// capturar o usuário autenticado no sistema
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");

			// consultar os compromissos no banco de dados
			CompromissoRepository compromissoRepository = new CompromissoRepository();

			List<Compromisso> lista = compromissoRepository.obterTodos(usuario.getIdUsuario(), dataMin, dataMax); // chamando
																													// o
																													// metodo
																													// sobrecarregado

			modelAndView.addObject("compromissos", lista); // mandando de volta para a pagina a lista de contatos
			modelAndView.addObject("dataMin", request.getParameter("dataMin")); // mandando de volta para a pagina os
																				// campos dataMin e dataMax
			modelAndView.addObject("dataMax", request.getParameter("dataMax"));

		} catch (Exception e) {

			modelAndView.addObject("mensagem_erro", e.getMessage());

		}

		return modelAndView;

	}

	@RequestMapping(value = "/excluir-compromisso")
	public ModelAndView excluirCompromisso(Integer id, HttpServletRequest request) {

		// WEB-INF/views/agenda/consulta.jsp
		ModelAndView modelAndView = new ModelAndView("agenda/consulta");

		try {

			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");

			Compromisso compromisso = new Compromisso();

			compromisso.setIdCompromisso(id);
			compromisso.setUsuario(usuario);

			// excluindo o compromisso
			CompromissoRepository compromissoRepository = new CompromissoRepository();

			compromissoRepository.excluir(compromisso);

			modelAndView.addObject("mensagem_sucesso", "Compromisso excluído com sucesso.");

			// carregando os compromissos que restaram na lista do usuario autenticado
			List<Compromisso> lista = compromissoRepository.obterTodos(usuario.getIdUsuario());

			// retornando o objeto lista para a consulta.jsp
			modelAndView.addObject("compromissos", lista);

		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}

		return modelAndView;

	}

}
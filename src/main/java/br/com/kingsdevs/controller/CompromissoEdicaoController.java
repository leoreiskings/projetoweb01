package br.com.kingsdevs.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.kingsdevs.entities.Compromisso;
import br.com.kingsdevs.entities.Usuario;
import br.com.kingsdevs.repositories.CompromissoRepository;

@Controller
public class CompromissoEdicaoController {

	@RequestMapping(value = "/editar-compromissos")
	public ModelAndView edicao(Integer id, HttpServletRequest request) { // metodo responsavel por abrir e carregar
																			// a pagina de edicao trazendo os dados do
																			// registro que sera editado

		// WEB-INF/views/agenda/edicao.jsp -> setando o caminho da pagina que será
		// aberta
		ModelAndView modelAndView = new ModelAndView("agenda/edicao");

		try {
			// capturando o usuario autenticado na sessao e atribuindo ao obj usuario
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");

			CompromissoRepository compromissoRepository = new CompromissoRepository();

			Compromisso compromisso = compromissoRepository.obterPorId(id, usuario.getIdUsuario());

			// variavel "compromisso" que será chamada na pagina edicao.jsp
			// o segundo valor é o objeto que possui os dados de retorno da query que foi
			// executada pelo metodo obterPorId
			modelAndView.addObject("compromisso", compromisso);

		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/atualizar-compromisso", method = RequestMethod.POST)
	public ModelAndView atualizarCompromisso(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("agenda/edicao");

		try {

			// capturando o usuario autenticado na sessao e atribuindo ao obj usuario
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");

			// Capturar todos os campos enviados pelo formulário
			Compromisso compromisso = new Compromisso();
			
			compromisso.setIdCompromisso(Integer.parseInt(request.getParameter("idCompromisso")));			
			compromisso.setNome(request.getParameter("nome"));
			compromisso.setData(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data")));
			compromisso.setHora(request.getParameter("hora"));
			compromisso.setDescricao(request.getParameter("descricao"));
			compromisso.setPrioridade(Integer.parseInt(request.getParameter("prioridade")));
			compromisso.setUsuario(usuario);

			//atualizando o compromisso
			CompromissoRepository compromissoRepository = new CompromissoRepository();
			compromissoRepository.atualizar(compromisso);
			
			modelAndView.addObject("mensagem_sucesso",	"Compromisso atualizado com sucesso.");
			
			modelAndView.addObject("compromisso", compromisso);
						
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}

		return modelAndView;

	}

	





}

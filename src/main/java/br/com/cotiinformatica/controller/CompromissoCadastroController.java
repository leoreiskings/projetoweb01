package br.com.cotiinformatica.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Compromisso;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.CompromissoRepository;

@Controller
public class CompromissoCadastroController {

	@RequestMapping(value = "/cadastrar-compromissos")
	public ModelAndView cadastro() {

		// WEB-INF/views/agenda/cadastro.jsp
		ModelAndView modelAndView = new ModelAndView("agenda/cadastro");

		return modelAndView;
	}

	@RequestMapping(value = "/criar-compromisso", method = RequestMethod.POST)
	public ModelAndView criarCompromisso(HttpServletRequest request) {

		// WEB-INF/views/agenda/cadastro.jsp
		ModelAndView modelAndView = new ModelAndView("agenda/cadastro");

		try {
			
			// resgatando os dados do Formulário
			String nome = request.getParameter("nome");
			Date data = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data"));
			String hora = request.getParameter("hora");
			String descricao = request.getParameter("descricao");
			Integer prioridade = Integer.parseInt(request.getParameter("prioridade"));

			/// criando um obj compromisso para gravar os dados no BD
			Compromisso compromisso = new Compromisso();
			
			compromisso.setNome(nome);
			compromisso.setData(data);
			compromisso.setHora(hora);
			compromisso.setDescricao(descricao);
			compromisso.setPrioridade(prioridade);
			
			compromisso.setUsuario((Usuario) request.getSession().getAttribute("usuario_autenticado")); // resgatando o usuario desse compromisso
			
			// cadastrar no banco de dados
			CompromissoRepository compromissoRepository = new CompromissoRepository();			
			compromissoRepository.inserir(compromisso);
			
			modelAndView.addObject("mensagem_sucesso", "Compromisso '" + compromisso.getNome()	+ "', cadastrado com sucesso.");			
			
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}

		
		return modelAndView;

	}

}

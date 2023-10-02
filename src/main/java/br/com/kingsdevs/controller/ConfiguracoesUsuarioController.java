package br.com.kingsdevs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.kingsdevs.entities.Usuario;
import br.com.kingsdevs.repositories.UsuarioRepository;

@Controller
public class ConfiguracoesUsuarioController {

	//metodo que carrega (abre) a pagina configuracoes.jsp
	@RequestMapping(value = "/configuracoes-usuario")
	public ModelAndView configuracoes() {
		
		//WEB-INF/views/agenda/configuracoes.jsp -> apos ser chamada pela camada de visao 
		//o sistema carrega configuracoes.jsp dentro do diretorio agenda 
		ModelAndView modelAndView 	= new ModelAndView("agenda/configuracoes");
		
	return modelAndView;
	}
	
	@RequestMapping(value = "/atualizar-senha", method = RequestMethod.POST )
	public ModelAndView atualizarSenha(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("agenda/configuracoes");
		
		try {
			
			//resgatar os dados do formulário
			String novaSenha = request.getParameter("novaSenha");
			
			String novaSenhaConfirmacao = request.getParameter("novaSenhaConfirmacao");
			
			if (novaSenha.equals(novaSenhaConfirmacao)) {
				
				
				//capturar o usuário autenticado na aplicação para ter certeza de qual senha alterar
				Usuario usuario = (Usuario) request	.getSession().getAttribute("usuario_autenticado");
	
				//atualizar a senha do usuário
				UsuarioRepository usuarioRepository 	= new UsuarioRepository();
				
				usuarioRepository.alterarSenha(usuario.getIdUsuario(), novaSenha);
				
				modelAndView.addObject	("mensagem_sucesso", "Senha atualizada com sucesso!");
				
			}else {
				
				modelAndView.addObject	("mensagem_erro", "Senhas não conferem, por favor tente	novamente.");				
				
			}			
			
		} catch (Exception e) {
			modelAndView.addObject	("mensagem_erro", e.getMessage());
		}		
		
		return modelAndView;
	}
	
	
	
}

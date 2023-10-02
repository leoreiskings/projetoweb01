package br.com.kingsdevs.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.kingsdevs.entities.Usuario;
import br.com.kingsdevs.messages.EmailMessage;
import br.com.kingsdevs.repositories.UsuarioRepository;

@Controller
public class PasswordController {

	@RequestMapping(value = "/password-recover")
	public ModelAndView password() throws IOException {

		// abrindo a pagina password.jsp
		/* WEB-INF/views/password.jsp */
		ModelAndView modelAndView = new ModelAndView("password");

		return modelAndView;

	}

	@RequestMapping(value = "/recuperar-senha", method = RequestMethod.POST)
	public ModelAndView recuperarSenha(HttpServletRequest request) throws IOException {
	
		
		/*WEB-INF/views/password.jsp*/
		ModelAndView modelAndView =  new ModelAndView("password");
		
		try {
			
			String email = request.getParameter("email");
			
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			
			//procurar o usuário no banco de dados através do email
			Usuario usuario = usuarioRepository.obterPorEmail(email);
			
			//verificar se o usuário foi encontrado
			if (usuario != null) {
				//gerando uma nova senha para o usuário
				 String novaSenha = String.valueOf(new Random().nextInt(999999999));
			
				//enviando a nova senha para o email do usuário
				EmailMessage.sendMessage(usuario.getEmail(), "Recuperação de Senha - AgendaWeb", "Olá, " + usuario.getNome() +
																									"\n\nSua senha foi redefinida com sucesso para que você possa acessar a agenda." +
																									"\nUtilize a senha: " + novaSenha + 
																									"\n\nVocê pode atualizar a senha futuramente através do menu Configurações do Usuário."	+ 
																									"\n\nAtt\nEquipe Kings Devs.");				
			
				//atualizando a senha no banco de dados
				usuarioRepository.alterarSenha(usuario.getIdUsuario(),	novaSenha);
				
				modelAndView.addObject("mensagem_sucesso",	"Recuperação de senha realizada com sucesso, por favor verifique seu email.");
			}
			else {
				modelAndView.addObject("mensagem_erro", "Email inválido. Usuário não encontrado.");
			}
			
			
		} catch (Exception e) {
			
			modelAndView.addObject("mensagem_erro", e.getMessage());
			
		}	
		
		return modelAndView;		
		
	}

}

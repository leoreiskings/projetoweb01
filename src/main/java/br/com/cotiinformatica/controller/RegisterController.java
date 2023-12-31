package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class RegisterController {

	@RequestMapping(value = "/register-user")
	public ModelAndView register() {

		// WEB-INF/views/register.jsp
		ModelAndView modelAndView = new ModelAndView("register");

		return modelAndView;
	}

	// M�todo para receber a requisi��o do formul�rio
	// POST -> /cadastrar-usuario
	@RequestMapping(value = "/cadastrar-usuario", method = RequestMethod.POST)
	public ModelAndView cadastrarUsuario(HttpServletRequest request) {
		// WEB-INF/views/register.jsp
		ModelAndView modelAndView = new ModelAndView("register");
		
		try {
				//recuperando todos os campos enviados pelo formul�rio
				String nome = request.getParameter("nome");
				String email = request.getParameter("email");
				String senha = request.getParameter("senha");
				String senhaConfirmacao = request.getParameter("senhaConfirmacao");
				
				//verificar se as senhas foram digitadas diferentes
				if( ! senha.equals(senhaConfirmacao)) {
					throw new Exception("Senhas n�o conferem, por favor verifique.");				
				}
				
				UsuarioRepository usuarioRepository = new UsuarioRepository();
				
				if(usuarioRepository.obterPorEmail(email) != null) { // se for diferente de null , significa que o metodo encontrou um email cadastrado e 
																	// entao dispara a msg de exce��o, pois email e nome j� est�o cadastrados
					
						throw new Exception("O email " + email + " informado j� est� cadastrado, por favor tente outro.");						
				}				
				
				Usuario usuario = new Usuario();
				
				usuario.setNome(nome);
				usuario.setEmail(email);
				usuario.setSenha(senha);
				
				usuarioRepository.inserir(usuario);
				
				modelAndView.addObject("mensagem_sucesso", "Parab�ns " + usuario.getNome() + ", sua conta foi criada com sucesso.");
				
			}
				catch(Exception e) {
					
				//gerando mensagem de erro
				modelAndView.addObject("mensagem_erro", e.getMessage());
			}
		
		return modelAndView;
	}

	
	
	
}
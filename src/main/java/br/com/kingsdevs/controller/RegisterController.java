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
public class RegisterController {

	//Método para abrir "CARREGAR A PAGINA" (ROTA)
	@RequestMapping(value = "/register-user")
	public ModelAndView register() throws IOException {
	//public ModelAndView register(HttpServletResponse response) throws IOException {	

		// WEB-INF/views/register.jsp
		ModelAndView modelAndView = new ModelAndView("register");

		return modelAndView;

	}

	@RequestMapping(value = "/cadastrar-usuario", method = RequestMethod.POST)
	public ModelAndView cadastrarUsuario(HttpServletRequest request) {

		// WEB-INF/views/register.jsp
		ModelAndView modelAndView = new ModelAndView("register");

		try {

			//  capturar todos os campos enviados pelo formulário
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			String senhaConfirmacao = request.getParameter("senhaConfirmacao");
			
			// verificar se as senhas foram digitadas diferentes
			if (!senha.equals(senhaConfirmacao)) {
				throw new Exception("Senhas não conferem, por favor verifique.");
			}
			
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			
			if(usuarioRepository.obterPorEmail(email) != null) { //antes de cadastrar busca no banco se já existe este email
				throw new Exception("O email " + email + " informado já está cadastrado, por favor tente outro.");
				}
			
			Usuario usuario = new Usuario();
			
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuario.setSenha(senha);
			
			
			usuarioRepository.inserir(usuario);
			
			modelAndView.addObject("mensagem_sucesso", "Parabéns " + usuario.getNome() + ", sua conta foi criada com sucesso.");

		} catch (Exception e) {
			//gerando mensagem de erro
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}

		return modelAndView;
	}

}

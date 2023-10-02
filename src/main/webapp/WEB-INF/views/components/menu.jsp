<nav class="navbar navbar-expand-lg bg-dark navbar-dark mb-4">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">Agenda de Compromissos</a>
		<button class="navbar-toggler" 
						type="button" 
						data-bs-toggle="collapse"
						data-bs-target="#navbarSupportedContent" 
						aria-controls="navbarSupportedContent" 
						aria-expanded="false" 
						aria-label="Toggle navigation">
				
				<span class="navbar-toggler-icon"></span>
			
		</button>
		
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<a 	class="nav-link" aria-current="page" href="/projetoweb01/home">Home</a>
				</li>
				
				<li class="nav-item dropdown">
					<a 	class="nav-link dropdown-toggle" 
							href="#" 
							id="navbarDropdown" 
							role="button" 
							data-bs-toggle="dropdown" aria-expanded="false">
							Gerenciar Compromissos 
					</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li>
							<a class="dropdown-item" href="/projetoweb01/cadastrar-compromissos">Cadastrar Compromissos</a>
						</li>
						<li>
							<a class="dropdown-item" href="/projetoweb01/consultar-compromissos">Consultar Compromissos</a>
						</li>												
					</ul>	
					
				</li>
				
				<li class="nav-item"><!-- este é o link exibido no navegador --> 
							<a class="nav-link" href="/projetoweb01/configuracoes-usuario">Configurações do Usuário</a>
						</li>						
			</ul>
			
			<form class="d-flex">			
			
				<div class="text-white">
					
					<small>
						<strong>Nome:  </strong>${usuario_autenticado.nome} |
						<strong>Email: </strong>${usuario_autenticado.email}
					</small>
				
					&nbsp;&nbsp;&nbsp;
				
					<a href="/projetoweb01/logout"
						class="btn btn-outline-secondary btn-sm"
						onclick="return confirm('Deseja realmente encerrar sua sessão?');">
						Sair da Agenda 
					</a>
				</div>	
						
			</form>
			
		</div>
	</div>
</nav>
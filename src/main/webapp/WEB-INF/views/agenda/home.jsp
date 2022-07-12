<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>COTI Informática - Agenda</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet" />

</head>

<body>
	<div class="container mt-3">

		<h3>Agenda de compromissos</h3>
		<h5>Seja bem vindo ao sistema!</h5>
		<hr />
		
		<div class="mb-1">
			Usuário autenticado: <strong>${usuario_autenticado.nome}</strong>
		</div>
		
		<div class="mb-1">
			Email: <strong>${usuario_autenticado.email}</strong>
		</div>
		
		<div class="mt-2">
			<a  href="/projetoweb01/logout"  
				class="btn btn-outline-secondary btn-sm" 
				onclick="return confirm('Deseja realmente encerrar sua sessão?');">Sair do Sistema </a>
		</div>
	
	</div>





	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>


</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>COTI Informática - Recuperar Senha</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body class="bg-secondary">
	<div class="container mt-5 col-md-4 offset-md-4 card card-body">

		<h5>Recuperação de Senha</h5>

		<p>Informe seu email para recuperar a senha de acesso.</p>

		<form method="post" action="recuperar-senha">
			<div class="mb-3">
				<label>Email:</label> 
					<input 	type="email" 
							class="form-control" 
							name="email" 
							placeholder="Digite o email aqui" 
							required="required" />
			</div>
			<!-- class="mb-2" MARGIN-BOTTOM (DÁ DISTANCIA DA MARGEM INFERIOR) O VALOR MAXIMO VAI DE 1 A 5 -->
			<div class="mb-2 d-grid">
				<input type="submit" value="Recuperar Senha" class="btn btn-success" />
			</div>
			
			<div class="mb-2 d-grid">
				<a href="/projetoweb01/" class="btn btn-light"> Voltar para a página de login </a>
			</div>
			
		</form>
		<!-- class="mt-2" MARGIN-TOP (DÁ DISTANCIA DA MARGEM SUPERIOR) -->
		<div class="mt-2 text-success">
			<strong>${mensagem_sucesso}</strong>
		</div>
		<div class="mt-2 text-danger">
			<strong>${mensagem_erro}</strong>
		</div>

	</div>

	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
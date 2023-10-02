<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agenda de Compromissos</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet" />

</head>
<body>

	<!-- menu do sistema -->
	<jsp:include page="/WEB-INF/views/components/menu.jsp"></jsp:include>

	<div class="container mt-3">
	
		<h3>Configurações de Usuário</h3>
		
		<h5>Informações do usuário autenticado no sistema.</h5>
		<hr />

		<div>Nome do usuário: <strong> ${usuario_autenticado.nome} </strong></div>

		<div>Email: <strong>${usuario_autenticado.email}</strong></div>
		<br />

		<form method="post" action="atualizar-senha">

			<div class="row mb-3">
			
				<div class="col-md-4">
					<label>Informe a nova senha:</label> 
					<input type="password"	class="form-control" name="novaSenha" required="required" />
				</div>
				
				<div class="col-md-4">
					<label>Confirme a nova senha:</label> 
					<input type="password"	class="form-control" name="novaSenhaConfirmacao" required="required" />
				</div>
				
			</div>

			<div class="row mb-3">			
				<div class="col-md-4">
					<input type="submit" value="Salvar Alterações" class="btn btn-success" />
				</div>
			</div>

			<div class="mb-3 text-success"><strong>${mensagem_sucesso}</strong></div>
			
			<div class="mb-3 text-danger"><strong>${mensagem_erro}</strong></div>

		</form>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>




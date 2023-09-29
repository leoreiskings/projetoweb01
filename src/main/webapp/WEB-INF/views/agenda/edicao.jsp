<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

	
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Edição de Compromissos</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet" />

</head>

<body>
	<!-- incluindo o component menu do sistema -->
	<jsp:include page="/WEB-INF/views/components/menu.jsp"></jsp:include>

	<div class="container mt-3">
		<h3>Edição de Compromissos</h3>
		<p>Utilize o formulário para editar compromissos em sua agenda.</p>
		<hr />

		<form method="post" action="atualizar-compromisso">
 
			<!-- campo oculto -->
			<input type="hidden" name="idCompromisso" value="${compromisso.idCompromisso}" />

			<!-- criando um formulario com 3 colunas a soma da largura deve ser 12 -->
			<div class="row mb-3">

				<div class="col-md-6">
					<label>Nome do compromisso:</label> 
						<input 	type="text" name="nome"
								class="formcontrol" placeholder="Digite aqui" 
								required="required"	value="${compromisso.nome}" />
				</div>
				
				<div class="col-md-3">
					<label>Data do compromisso:</label> 
						<input 	type="date" name="data"
								class="formcontrol" required="required"
								value='<fmt:formatDate value="${compromisso.data}" pattern="yyyy-MM-dd"/>' />
				</div>
				
				<div class="col-md-3">
					<label>Hora do compromisso:</label> 
						<input 	type="time" name="hora"
								class="formcontrol" required="required"
								value="${compromisso.hora}" />
				</div>

			</div>

			<div class="row mb-3">
				<div class="col-md-9">
					<label>Descrição da tarefa</label>
					<textarea name="descricao" class="form-control" required="required">${compromisso.descricao}</textarea>
				</div>
				<div class="col-md-3">
					<label>Prioridade da tarefa:</label> 
						<select name="prioridade" class="form-select" required="required">
							<option value="">Selecione</option>
							
							<option ${compromisso.prioridade == 1 ? "selected" : ""} value="1">ALTA</option>
							<option ${compromisso.prioridade == 2 ? "selected" : ""} value="2">MÉDIA</option>
							<option ${compromisso.prioridade == 3 ? "selected" : ""} value="3">BAIXA</option>
						</select>
				</div>
			</div>
			
			<div class="row mb-3">
			
				<div class="col-md-12">
					<input type="submit" value="Salvar Alterações"	class="btn btn-primary" /> 
					<a href="/projetoweb01/consultar-compromissos" 	class="btn btn-light">Voltar para a consulta</a>
				</div>
				
			</div>
			
		</form>
		
		<!-- LOCAL DE EXIBIÇÃO DAS MSGs DE ERRO OU DE SUCESSO! -->
		<div class="mt-2">
			<strong class="text-success">${mensagem_sucesso}</strong>
		</div>

		<div class="mt-2">
			<strong class="text-danger">${mensagem_erro}</strong>
		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Cadastro de Compromissos</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet" />

</head>

<body>
	<!-- incluindo o component menu do sistema -->
	<jsp:include page="/WEB-INF/views/components/menu.jsp"></jsp:include>

	<div class="container mt-3">
		<h3>Cadastro de Compromissos</h3>
		<p>Preencha o formulário para incluir compromissos em sua agenda.</p>
		<hr />
		
		<form method="post" action="criar-compromisso">
			
			<!-- criando um formulario com 3 colunas a soma da largura deve ser 12 -->
			<div class="row mb-3">
				
				<div class="col-md-6" ><!-- 1ªcoluna com largura 6 -->
					<label>Nome do compromisso:</label> 
					<input type="text" name="nome" class="form-control" 
							placeholder="Digite aqui" required="required" />
				</div>
				
				<div class="col-md-3"><!-- 2ª coluna com largura 3 -->
					<label>Data do compromisso:</label> 
					<input type="date" name="data" class="form-control" required="required" />
				</div>
				
				<div class="col-md-3"><!-- 3ª coluna com largura 3 -->
					<label>Hora do compromisso:</label> 
					<input type="time" name="hora" class="form-control" required="required" />
				</div>
			</div>
			
			<div class="row mb-3">
				<div class="col-md-9">
					<label>Descrição da tarefa</label>
					<textarea name="descricao" class="form-control" placeholder="Digite aqui" required="required"></textarea>
				</div>
				<div class="col-md-3">
					<label>Prioridade da tarefa:</label> 
						<select name="prioridade" class="form-select" required="required">
						<option value="">Selecione</option>
						<option value="1">ALTA</option>
						<option value="2">MÉDIA</option>
						<option value="3">BAIXA</option>
					</select>
				</div>
			</div>
			<div class="row mb-3">
				<div class="col-md-12">
					<input type="submit" value="Realizar Cadastro"	class="btn btn-success" /> 
					<input type="reset" value="Limpar" class="btn btn-light" />
				</div>
			</div>
		</form>
		
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
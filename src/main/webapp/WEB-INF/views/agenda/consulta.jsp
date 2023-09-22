<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- area de import -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta de Compromissos</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet" />

</head>
<body>

	<jsp:include page="/WEB-INF/views/components/menu.jsp"></jsp:include>


	<div class="container mt-3">
		<h3>Consulta de Compromissos</h3>
		<p>Pesquise os compromissos cadastrados em sua agenda.</p>
		<hr />
		<p>Selecione o período de datas para consulta:</p>

		<form method="post" action="pesquisar-compromissos">
			<div class="row">
				<div class="col-md-2">
					<input type="date" class="form-control" name="dataMin"
						required="required" />
				</div>
				<div class="col-md-2">
					<input type="date" class="form-control" name="dataMax"
						required="required" />
				</div>
				<div class="col-md-3">
					<input type="submit" class="btn btn-success" value="Pesquisar" />
				</div>
			</div>
		</form>


		<div class="text-danger">
			<strong>${mensagem_erro}</strong>
		</div>

		<table class="table table-hover table-sm mt-4">
			<!-- cabecalho da pagina -->
			<thead>
				<tr>
					<th>Nome do compromisso</th>
					<th>Data</th>
					<th>Hora</th>
					<th>Descrição</th>

					<th>Prioridade</th>
					<th>Operações</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${compromissos}" var="item">
					<tr>
						<td>${item.nome}</td>
						<td><fmt:formatDate value="${item.data}" pattern="dd/MM/yyyy" />
						</td>
						<td>${item.hora}</td>
						<td>${item.descricao}</td>
						<td>
							<c:if test="${item.prioridade == 1}">
								<span class="badge bg-danger">ALTA</span>
							</c:if> 
							<c:if test="${item.prioridade == 2}">
								<span class="badge bg-warning">MÉDIA</span>
							</c:if> 
							<c:if test="${item.prioridade == 3}">
								<span class="badge bg-success">BAIXA</span>
							</c:if>
						</td>
						<td>
							<a href="#" class="btn btn-primary btn-sm"> Editar </a> 
							<a href="#" class="btn btn-danger btn-sm"> Excluir </a>
						</td>
					</tr>
				</c:forEach>
			</tbody>

			<tfoot>
				<tr>
					<td colspan="6">Quantidade de compromissos:	${compromissos.size()}</td>
				</tr>
			</tfoot>
		</table>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
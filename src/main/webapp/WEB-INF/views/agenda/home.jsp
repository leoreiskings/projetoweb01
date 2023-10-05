<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>[Agenda de Compromissos]</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet" />

</head>

<body>

	<!-- menu do sistema -->
	<jsp:include page="/WEB-INF/views/components/menu.jsp"></jsp:include>
	
	<div class="container mt-3">
		<h3>Agenda de compromissos</h3>
		<h5>Seja bem vindo ao sistema!</h5>
		<hr />
		
		<form method="post" action="pesquisar-compromissos-mes">
		
			<div class="row">
			
					<div class="col-md-2 text-end">
						<div class="mt-2">Mês vigente:</div>
					</div>
					
					<div class="col-md-2">
						<select name="mes" class="form-select">
							<option value="1" ${mes_vigente == 1 ? "selected"  :  ""}>Janeiro</option>
							<option value="2" ${mes_vigente == 2 ? "selected"  :  ""}>Fevereiro</option>
							<option value="3" ${mes_vigente == 3 ? "selected"  :  ""}>Março</option>
							<option value="4" ${mes_vigente == 4 ? "selected"  :  ""}>Abril</option>
							<option value="5" ${mes_vigente == 5 ? "selected"  :  ""}>Maio</option>
							<option value="6" ${mes_vigente == 6 ? "selected"  :  ""}>Junho</option>
							<option value="7" ${mes_vigente == 7 ? "selected"  :  ""}>Julho</option>
							<option value="8" ${mes_vigente == 8 ? "selected"  :  ""}>Agosto</option>
							<option value="9" ${mes_vigente == 9 ? "selected"  :  ""}>Setembro</option>
							<option value="10" ${mes_vigente == 10 ? "selected"  :  ""}>Outubro</option>
							<option value="11" ${mes_vigente == 11 ? "selected"  :  ""}>Novembro</option>
							<option value="12" ${mes_vigente == 12 ?"selected"  :  ""}>Dezembro</option>
						</select>
					</div>
					<div class="col-md-3">
						<input type="submit" value="Selecionar" class="btn btn-success" />
					</div>
					
					<div class="col-md-5 text-end">
						<div class="mt-2">
														Período de <strong><fmt:formatDate value="${data_inicio}" pattern="EE, dd/MM/yyyy" /></strong> a 
																			<strong><fmt:formatDate value="${data_fim}" pattern="EE, dd/MM/yyyy" /></strong>
						</div>
					</div>
				
			</div>
			
		</form>
			<div class="row">
				<div class="col-md-8">
						<table class="table table-hover table-sm mt-4">
							<thead>
								<tr>
									<th>Nome do compromisso</th>
									<th>Data</th>
									<th>Hora</th>
									<th>Prioridade</th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${compromissos}" var="item">
									<tr>
										<td>${item.nome}</td>
										<td><fmt:formatDate value="${item.data}" pattern="dd/MM/yyyy" /></td>
										<td>${item.hora}</td>
										<td>
												<c:if test="${item.prioridade == 1}"><span class="badge bg-danger">ALTA</span></c:if> 
										        <c:if test="${item.prioridade == 2}"><span class="badge bg-warning">MÉDIA</span></c:if> 
										        <c:if test="${item.prioridade == 3}"><span class="badge bg-success">BAIXA</span>	</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="6">Quantidade de compromissos: ${compromissos.size()}</td>
								</tr>
							</tfoot>
						</table>
				</div>
				<div class="col-md-4">
				
					<div id="grafico"></div>
				
				
				
				</div>
			</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>	
	<script src="https://code.highcharts.com/highcharts.js"></script>
	
	<script>
			var array = [];
			
			array.push(['Prioridade Baixa', ${qtd_prioridade_baixa}]);
			array.push(['Prioridade Média', ${qtd_prioridade_media}]);
			array.push(['Prioridade Alta', ${qtd_prioridade_alta}]);
			
			new Highcharts.Chart({
					chart: {
							type : 'pie',
							renderTo: 'grafico'
					},
					plotOptions: {
							pie : {
									innerSize: '60%',
									allowPointSelect: true,
						cursor: 'pointer',
						dataLabels: {
								enabled: false,
						}
								}
					},
					title: {
							text : "Compromissos por prioridade"
					},
					series: [
							{ data: array }
					],
					colors: ['#5cb85c', '#f0ad4e', '#d9534f']
			});
 </script>
	
	
	
	
	
	
	
	
</body>

</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css" />
<title>Candidatos</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">Elecciones</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Candidato <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="candidato?accion=showInsert">Registrar</a></li>
							<li><a href="candidato?accion=list">Candidatos</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Votante <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="votante?accion=showInsert">Registrar</a></li>
							<li><a href="votante?accion=list">Votantes</a></li>
						</ul></li>
					<li class="active"><a href="votante?accion=showValidate">Votar</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<h3 class="text-center title" style="margin-top: 1em; margin-bottom: 2em">Candidatos</h3>
		<table class="table table-bordered" id="mytable">
			<thead>
				<tr>
					<th>ID</th>
					<th>Documento</th>
					<th>Nombre</th>
					<th>Apellido</th>
					<th>Eleccion</th>
					<th>Numero</th>
					<th>Opcion</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="u" items="${list}">
					<tr>
						<td><c:out value="${u.id}"></c:out></td>
						<td><c:out value="${u.documento}"></c:out></td>
						<td><c:out value="${u.nombre}"></c:out></td>
						<td><c:out value="${u.apellido}"></c:out></td>
						<td><c:out value="${u.eleccionBean.nombre}"></c:out></td>
						<td><c:out value="${u.numero}"></c:out></td>
						<td><a
							href="candidato?accion=showEdit&&id=<c:out value = '${u.id}' />">Editar
						</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
							href="candidato?accion=delete&&id=<c:out value='${u.id}' />">Delete</a></td>
				</c:forEach>

			</tbody>

		</table>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/form.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>
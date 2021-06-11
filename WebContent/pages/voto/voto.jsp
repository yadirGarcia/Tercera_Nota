<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css" />
<link href="${pageContext.request.contextPath}/resources/css/card.css"
	rel="stylesheet" type="text/css">
<title><c:out
		value="${votanteValidado ? votante.documento : '404'}" /></title>
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
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/index.jsp">Elecciones</a>
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
	<c:choose>
		<c:when test="${votanteValidado}">
			<div class="cards">
				<c:forEach var="usuario" items="${listCandidato}" varStatus="loop">
					<div class="card"
						style="width: 400px; height: 400px; background-image: url(https://picsum.photos/400/1200?random=${loop.index + 1})">
						<div class="card__img"></div>
						<div class="card__info">
							<span class="card__category">Candidato:</span>
							<h3 class="card__title">
								<c:out value="${usuario.nombre} ${usuario.apellido}"></c:out>
							</h3>
							<h3 class="card__title">
								<c:out value="#${usuario.numero}"></c:out>
							</h3>
							<span class="card__by"><a
								href="voto?accion=votar&&candidato=${usuario.id}&&usuario=${votante.documento}&&eleccion=${eleccion}"
								class="card__author" title="author">Votar</a></span>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:when>
		<c:when test="${!votanteValidado}">
			<div class="container">
				<h1 class="title">No tienes permisos para votar</h1>
				<a href="${pageContext.request.contextPath}/index.jsp">Inicio</a>
			</div>
		</c:when>
	</c:choose>
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
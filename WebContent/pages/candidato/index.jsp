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
<title>${candidato == null ? "Registrar" : "Actualizar"}
	Candidato</title>
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
		<form class="well form-horizontal"
			action="${candidato != null ? 'candidato?accion=edit' : 'candidato?accion=insert'}"
			method="post" id="formulario-elecciones-1">
			<fieldset>

				<legend>
					<c:out value="${candidato == null ? 'Registrar' : 'Actualizar'}"></c:out>
					Candidato
				</legend>
				<div class="form-group" style="display: none">
					<label class="col-md-4 control-label">ID</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input name="id"
								value="${candidato.id}" id="id" placeholder="id"
								class="form-control" type="text">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">Documento</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input name="documento"
								value="${candidato.documento}" id="documento"
								placeholder="Documento" class="form-control" type="text">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">Nombre</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input name="nombre"
								value="${candidato.nombre}" id="nombre" placeholder="Nombre"
								class="form-control" type="text">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">Apellido</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input name="apellido"
								value="${candidato.apellido}" id="apellido"
								placeholder="Apellido" class="form-control" type="text">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">Elección</label>
					<div class="col-md-4 selectContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-list"></i></span> <select name="eleccion"
								id="eleccion" class="form-control selectpicker">
								<option
									value="${candidato != null ? candidato.eleccionBean.id : ''}">${candidato != null ? candidato.eleccionBean.id : "Seleccione Una"}</option>
								<c:forEach var="dd" items="${list}">
									<option value='${dd.id}'><c:out value='${dd.nombre}' /></option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">Numero</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input name="numero"
								value="${candidato.numero}" id="numero" placeholder="Numero"
								class="form-control" type="text">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<button type="submit" class="btn btn-warning">
							<c:out value="${candidato == null ? 'Registrar' : 'Actualizar'}"></c:out>
							<span class="glyphicon glyphicon-send"></span>
						</button>
					</div>
				</div>

			</fieldset>
		</form>

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
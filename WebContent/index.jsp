<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/css/card.css"
	rel="stylesheet">
<title>Elecciones | Menu</title>
</head>
<body>

	<div class="container-main-cards">
	<div class="cards">
		<a href="candidato?accion=list">
			<div class="card"
				style="width: 400px; height: 300px; background-image: url(https://ichef.bbci.co.uk/news/640/cpsprodpb/9665/production/_113810583_index_promo_poll_tracker_bw_976.png)">
			</div>
		</a> 
		<a href="votante?accion=list">
			<div class="card"
				style="width: 400px; height: 300px; background-image: url(https://cde.canaln.pe/actualidad-elecciones-2021-conoce-pasos-que-deben-seguir-votantes-11-abril-n433316-1200x630-965815.jpg)">
			</div>
		</a>
		<a href="votante?accion=showValidate">
			<div class="card"
				style="width: 400px; height: 300px; background-image: url(https://www.portafolio.co/files/article_multimedia/uploads/2018/03/11/5aa551e55a96d.jpeg)">
			</div>
		</a>
	</div>
	
	</div>
	
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<!--
	Landed by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>iFactura consumidor</title>
<meta http-equiv="Content-Type" content="text/html" charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="assets/css/main.css" />
<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
</head>
<body class="landing">
	<div id="page-wrapper">

		<!-- Header -->
		<header id="header">
			<h1 id="logo">
				<a href="/">iFactura consumidor</a>
			</h1>
			<nav id="nav">
				<ul>
					<li><a href="/">Inicio</a></li>
					<li><a href="/addFactura">Añadir factura</a>
						<ul class="dropdown">
							<li><a href="/addFactura">Manualmente</a></li>
							<li><a href="#">Escanear PDF</a></li>
						</ul></li>
					<li><a href="/showFactura">Mostrar facturas</a></li>
					<li><a href="#">Apuntarse a compra colectiva</a></li>
					<li><a class="button special" href="<c:url value="${url}"/>"><c:out
								value="${urlLinktext}" /></a></li>
					<li><c:if test="${not empty user}">
							<c:out value="${user.nickname}" />
						</c:if></li>
				</ul>
			</nav>
		</header>
		<div id="main" class="wrapper style1">
			<div class="container">
				<!-- Table -->
				<section>
					<h3>Tus facturas</h3>
					<div class="table-wrapper">
						<table>
							<thead>
								<tr>
									<th>Empresa telefónica</th>
									<th>Periodo de facturación</th>
									<th>Municipio</th>
									<th>Provincia</th>
									<th>Cuotas</th>
									<th>Consumos</th>
									<th>Importe antes de impuestos</th>
									<th>Importe total a pagar</th>
									<th>Datos contratados</th>
									<th>Minutos contratados</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${facturas}" var="facturasi">
									<tr>
										<form method="post" action="/showFactura">
											<td><c:out value="${facturasi.empresa}" /></td>
											<td><c:out value="De: ${facturasi.startDate} a: ${facturasi.endDate}" /></td>
											<td><c:out value="${facturasi.municipio}" /></td>
											<td><c:out value="${facturasi.provincia}" /></td>
											<td><c:out value="${facturasi.cuotas}" /></td>
											<td><c:out value="${facturasi.consumos}" /></td>
											<td><c:out value="${facturasi.sinImpuestos}" /></td>
											<td><c:out value="${facturasi.importeTotal}" />€</td>
											<td><c:out value="${facturasi.datosContratados}" /></td>
											<td><c:out value="${facturasi.minutosContratados}" /></td>
											<input id="id_factura" name="id_factura" type="hidden" value="${facturasi.id}" />

											<td><input type="submit" value="Borrar"></td>
										</form>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</section>
			</div>
		</div>
		<!-- Footer -->
		<footer id="footer">
			<p>
				<a href="/"> iFactura </a>
			</p>
			<p>
				<a href="http://www.etsit.upm.es"> ETSI Telecomunicación @ 2015
				</a> <a href="http://www.upm.es"> Universidad Politécnica de Madrid
				</a>
			</p>
		</footer>

	</div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/jquery.dropotron.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/skel.min.js"></script>
	<script src="assets/js/util.js"></script>
	<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
	<script src="assets/js/main.js"></script>
</body>
</html>

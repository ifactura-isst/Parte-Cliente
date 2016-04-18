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
		<h1 id="logo"><a href="/">iFactura consumidor</a></h1>
			<nav id="nav">
				<ul>
					<li><a href="/">Inicio</a></li>
					<li><a href="/addFactura">Añadir factura</a></li>
					<li><a href="/showFactura">Mostrar facturas</a></li>
					<li><a href="#">Apuntarse a compra colectiva</a></li>
					<li><a class="button special" href="<c:url value="${url}"/>"><c:out value="${urlLinktext}"/></a></li>
					<li><c:if test="${not empty user}"><c:out value="${user.nickname}"/></c:if></li>
				</ul>
			</nav>
	</header>
    <div id="main" class="wrapper style1">
        <div class="container">
            <!-- Banner -->
            <section id="banner">
                <div class="content menu-ancho text-center">
                    <form method="post" action="/addFactura">
                            <fieldset id="factura_nueva">
                                    <h2>Datos Factura</h2>
                                    <p><span><label for="name">Nombre
                                        <input type="text" name="name" id="name" minlength="3" maxlength="20" required placeholder="Introduzca su nombre">
                                    </label></span>
                                    <span><label for="surname">Apellidos
                                        <input type="text" name="surname" id="surname" required placeholder="Introduzca sus apellidos">
                                    </label></span></p>
                                    <p><label for="municipio">Municipio
                                        <input type="text" name="municipio" id="municipio" required placeholder="Municipio">
                                    </label></p>
                                    <p><label for="provincia">Provincia
                                        <select type="text "name="provincia" id="provincia" required>
                                            <option value="Álava">Álava</option>
                                            <option value="Albacete">Albacete</option>
                                            <option value="Alicante">Alicante</option>
                                            <option value="Almería">Almería</option>
                                            <option value="Asturias">Asturias</option>
                                            <option value="Ávila">Ávila</option>
                                            <option value="Badajoz">Badajoz</option>
                                            <option value="Barcelona">Barcelona</option>
                                            <option value="Burgos">Burgos</option>
                                            <option value="Cáceres">Cáceres</option>
                                            <option value="Cádiz">Cádiz</option>
                                            <option value="Cantabria">Cantabria</option>
                                            <option value="Castellón">Castellón</option>
                                            <option value="Ciudad Real">Ciudad Real</option>
                                            <option value="Córdoba">Córdoba</option>
                                            <option value="La Coruña">La Coruña</option>
                                            <option value="Cuenca">Cuenca</option>
                                            <option value="Gerona">Gerona</option>
                                            <option value="Granada">Granada</option>
                                            <option value="Guadalajara">Guadalajara</option>
                                            <option value="Guipúzcoa">Guipúzcoa</option>
                                            <option value="Huelva">Huelva</option>
                                            <option value="Huesca">Huesca</option>
                                            <option value="Islas Baleares">Islas Baleares</option>
                                            <option value="Jaén">Jaén</option>
                                            <option value="León">León</option>
                                            <option value="Lérida">Lérida</option>
                                            <option value="Lugo">Lugo</option>
                                            <option value="Madrid">Madrid</option>
                                            <option value="Málaga">Málaga</option>
                                            <option value="Murcia">Murcia</option>
                                            <option value="Navarra">Navarra</option>
                                            <option value="Orense">Orense</option>
                                            <option value="Palencia">Palencia</option>
                                            <option value="Las Palmas">Las Palmas</option>
                                            <option value="Pontevedra">Pontevedra</option>
                                            <option value="La Rioja">La Rioja</option>
                                            <option value="Salamanca">Salamanca</option>
                                            <option value="Segovia">Segovia</option>
                                            <option value="Sevilla">Sevilla</option>
                                            <option value="Soria">Soria</option>
                                            <option value="Tarragona">Tarragona</option>
                                            <option value="Santa Cruz de Tenerife">Santa Cruz de Tenerife</option>
                                            <option value="Teruel">Teruel</option>
                                            <option value="Valencia">Valencia</option>
                                            <option value="Valladolid">Valladolid</option>
                                            <option value="Vizcaya">Vizcaya</option>
                                            <option value="Zamora">Zamora</option>
                                            <option value="Zaragoza">Zaragoza</option>
                                            <option value="Ceuta">Ceuta</option>
                                            <option value="Melilla">Melilla</option>
                                        </select>
                                    </label></p>

                                <p><label for="empresa">Empresa proveedora
                                    <input type="text" name="empresa" id="empresa" required placeholder="Empresa proveedora">
                                </label></p>

                            <fieldset id="datos_factura">
                                <label for="start_date">Periodo de facturación
                                    <p><span>De:
                                        <input type="date" name="start_date" id="start_date" required placeholder="dd/mm/aaaa">
                                    </span>
                                    <span>Hasta:
                                        <input type="date" name="end_date" id="end_date" required placeholder="dd/mm/aaaa">
                                    </span></p>
                                </label>
                            </fieldset>

                            <fieldset id="tipo factura">
                                    <p><label for="tipoFactura">Tipo de factura
                                        <select type="text" name="tipoFactura" id="tipoFactura" required>
                                            <option value="luz">Luz</option>
                                            <option value="agua">Agua</option>
                                            <option value="gas">Gas</option>
                                            <option value="teléfono">Teléfono</option>
                                        </select>
                                    </label></p>
                            </fieldset>

                            <fieldset id="resumen_factura">
                                <p><label for="total">Importe
                                    <input type="text" name="total" id="total" size="35" required placeholder="Introduzca el importe total"><span>€</span>
                                </label></p>
                            </fieldset>

                            <p><input type="submit" value="Enviar"><input type="reset"></p>
                        </fieldset>

                    </form>

                </div>

            </section>
        </div>
    </div>
    <!-- Footer -->
    <footer id="footer">
        <p>
            <a href="/">
                iFactura
            </a>
        </p>
        <p>
            <a href="http://www.etsit.upm.es">
                ETSI Telecomunicación @ 2015
            </a>
            <a href="http://www.upm.es">
                Universidad Politécnica de Madrid
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

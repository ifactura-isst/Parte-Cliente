<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Page Description">
    <meta name="author" content="alvaro">
    <title>iFactura-Consumidor</title>

    <!-- Bootstrap -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- My CSS -->
    <link href="assets/css/custom.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-2">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Consumidor<img class="logo" src="images/factura.jpg" alt="iFactura" /></a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
            <ul class="nav navbar-nav">
                <li><a href="/"><span class="glyphicon glyphicon-home"></span> Inicio <span class="sr-only">(current)</span></a></li>
                <li class="dropdown active">
                    <a href="/addFactura" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-expanded="false"><span class="glyphicon glyphicon-folder-open"></span> Añadir factura <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="/addFactura">Manualmente</a></li>
                        <li class="divider"></li>
                        <li><a href="/addFacturaPDF">Escanear PDF</a></li>
                    </ul>
                </li>
                <li><a href="/showFactura"><span class="glyphicon glyphicon-eye-open"></span> Mostrar factura</a></li>
                <li><a href="/comprasColectivas"><span class="glyphicon glyphicon-ok"></span> Apuntarse a compra colectiva</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right btn-primary active">
                <li><a href="<c:url value="${url}"/>"><span class="glyphicon glyphicon-log-in"></span> <c:out value="${urlLinktext}"/></a></li>
            </ul>
            <form class="navbar-form navbar-right" role="search">
                <div class="form-group">
                    <input class="form-control" placeholder="Buscar..." type="text">
                </div>
            </form>
        </div>
    </div>
</nav>

<div class="container">
    <form class="form-horizontal" method="post" action="/addFactura">
        <fieldset>
            <legend>Introduce los datos de tu factura</legend>
            <div class="form-group">
                <label for="municipio" class="col-lg-2 control-label">Municipio</label>
                <div class="col-lg-10">
                    <input class="form-control" id="municipio" name="municipio" placeholder="Municipio" type="text" required>
                </div>
            </div>
            <div class="form-group">
                <label for="provincia" class="col-lg-2 control-label">Provincia</label>
                <div class="col-lg-10">
                    <select class="form-control" id="provincia" name="provincia" placeholder="Provincia" type="text" required>
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
                        <option value="Toledo">Toledo</option>
                        <option value="Valencia">Valencia</option>
                        <option value="Valladolid">Valladolid</option>
                        <option value="Vizcaya">Vizcaya</option>
                        <option value="Zamora">Zamora</option>
                        <option value="Zaragoza">Zaragoza</option>
                        <option value="Ceuta">Ceuta</option>
                        <option value="Melilla">Melilla</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="empresa" class="col-lg-2 control-label">Empresa proveedora</label>
                <div class="col-lg-10">
                    <input class="form-control" id="empresa" name="empresa" placeholder="Empresa telefónica" type="text" required>
                </div>
            </div>
            <div class="form-group">
                <label for="start_date" class="col-lg-2 control-label">Periodo de facturación</label>
                <div class="col-lg-10">
                    <span>De:
                        <input class="form-control" id="start_date" name="start_date" placeholder="dd/mm/aaaa" type="text" required>
                    </span>
                    <span>Hasta:
                        <input class="form-control" id="end_date" name="end_date" placeholder="dd/mm/aaaa" type="text" required>
                    </span>
                </div>
            </div>
            <div class="form-group">
                <label for="cuotas" class="col-lg-2 control-label">Tarifa</label>
                <div class="col-lg-10">
                    <input class="form-control" id="cuotas" name="cuotas" placeholder="Introduzca el importe de su tarifa" type="text" required>
                </div>
            </div>
            <div class="form-group">
                <label for="consumos" class="col-lg-2 control-label">Consumo extra</label>
                <div class="col-lg-10">
                    <input class="form-control" id="consumos" name="consumos" placeholder="Introduzca el importe de su consumo extra" type="text" required>
                </div>
            </div>
            <div class="form-group">
                <label for="sinImpuestos" class="col-lg-2 control-label">Importe antes de impuestos</label>
                <div class="col-lg-10">
                    <input class="form-control" id="sinImpuestos" name="sinImpuestos" placeholder="Introduzca el importe total antes de impuestos" type="text" required>
                </div>
            </div>
            <div class="form-group">
                <label for="total" class="col-lg-2 control-label">Importe total</label>
                <div class="col-lg-10">
                    <input class="form-control" id="total" name="total" placeholder="Introduzca el importe total a pagar" type="text" required>
                </div>
            </div>
            <div class="form-group">
                <label for="datos" class="col-lg-2 control-label">Datos contratados</label>
                <div class="col-lg-10">
                    <input class="form-control" id="datos" name="datos" placeholder="Introduzca los datos contratados (GB)" type="text" required>
                </div>
            </div>
            <div class="form-group">
                <label for="minutos" class="col-lg-2 control-label">Minutos contratados</label>
                <div class="col-lg-10">
                    <input class="form-control" id="minutos" name="minutos" placeholder="Introduzca los minutos contratados" type="text" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <button type="submit" class="btn btn-primary">Añadir</button>
                    <button type="reset" class="btn btn-default">Cancelar</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>

<div id="footer" class="panel-footer">
    <p><a href="/">iFactura</a></p>
    <p>
        <a href="http://www.etsit.upm.es">ETSI Telecomunicación @ 2016</a>
        <a href="http://www.upm.es">Universidad Politécnica de Madrid</a>
    </p>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="assets/js/bootstrap.min.js"></script>
</body>
</html>
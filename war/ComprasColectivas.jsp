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
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Consumidor<img class="logo" src="images/factura.jpg" alt="iFactura" /></a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
            <ul class="nav navbar-nav">
                <li><a href="/"><span class="glyphicon glyphicon-home"></span> Inicio</a></li>
                <li class="dropdown">
                    <a href="/addFactura" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                    aria-expanded="false"><span class="glyphicon glyphicon-folder-open"></span> Añadir factura <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="/addFactura">Manualmente</a></li>
                        <li class="divider"></li>
                        <li><a href="/addFacturaPDF">Escanear PDF</a></li>
                    </ul>
                </li>
                <li><a href="/showFactura"><span class="glyphicon glyphicon-eye-open"></span> Mostrar factura</a></li>
                <li class="active"><a href="/comprasColectivas"><span class="glyphicon glyphicon-ok"></span> Apuntarse a compra colectiva</a></li>
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
    <table class="table table-hover table-striped">
        <thead>
        <tr>
            <th>Título</th>
            <th>Descripción</th>
            <th>Usuarios apuntados</th>
            <th>Plazas disponibles</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${subastas}" var="subastasi">
            <tr>
                <form method="post" action="/comprasColectivas">
                    <td><c:out value="${subastasi.title}" /></td>
                    <td><c:out value="${subastasi.description}" /></td>
                    <td><c:out value="${subastasi.userApuntados}" /></td>
                    <td><c:out value="${subastasi.userMax}" /></td>
                    <input id="id_subasta" name="id_subasta" type="hidden" value="${subastasi.id}" />
                    <input id="usuarios_apuntados" name="usuarios_apuntados" type="hidden" value="${subastasi.userApuntados}" />
                    <input id="usuarios_max" name="usuarios_max" type="hidden" value="${subastasi.userMax}" />

                    <td><button<c:if test="${subastasi.state == true}"> disabled</c:if> class="btn btn-info" type="submit">Apuntarse <span class="glyphicon glyphicon-check"></span></button></td>
                </form>
                <form method="post" action="/comprasColectivasUncheck">
                	<input id="id_subasta" name="id_subasta" type="hidden" value="${subastasi.id}" />
                    <input id="usuarios_apuntados" name="usuarios_apuntados" type="hidden" value="${subastasi.userApuntados}" />
                    <input id="usuarios_max" name="usuarios_max" type="hidden" value="${subastasi.userMax}" />
                	<td><button<c:if test="${subastasi.state == true}"> disabled</c:if> class="btn btn-primary" type="submit">Desapuntarse <span class="glyphicon glyphicon-unchecked"></span></button></td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
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
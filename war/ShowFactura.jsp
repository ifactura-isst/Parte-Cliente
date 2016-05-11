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
                <a class="navbar-brand" href="/">iFactura-Consumidor</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
                <ul class="nav navbar-nav">
                    <li><a href="/">Inicio <span class="sr-only">(current)</span></a></li>
                    <li class="dropdown">
                        <a href="/addFactura" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Añadir factura <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/addFactura">Manualmente</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Escanear PDF</a></li>
                        </ul>
                    </li>
                    <li class="active"><a href="/showFactura">Mostrar factura</a></li>
                    <li><a href="#">Apuntarse a compra colectiva</a></li>
                </ul>
                <form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input class="form-control" placeholder="Buscar..." type="text">
                    </div>
                </form>
                <ul class="nav navbar-nav navbar-right btn-primary active">
                    <li><a href="<c:url value="${url}"/>"><c:out value="${urlLinktext}"/></a></li>
                    <li><c:if test="${not empty user}"><c:out value="${user.nickname}"/></c:if></li>
                </ul>
            </div>
        </div>
    </nav>
    
    <div class="container">
            <table class="table table-hover table-striped">
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
                                <td><c:out value="${facturasi.cuotas}€" /></td>
                                <td><c:out value="${facturasi.consumos}€" /></td>
                                <td><c:out value="${facturasi.sinImpuestos}€" /></td>
                                <td><c:out value="${facturasi.importeTotal}€" /></td>
                                <td><c:out value="${facturasi.datosContratados}GB" /></td>
                                <td><c:out value="${facturasi.minutosContratados}min" /></td>
                                <input id="id_factura" name="id_factura" type="hidden" value="${facturasi.id}" />

                                <td><input class="btn btn-info" type="submit" value="Borrar"></td>
                            </form>
                        </tr>
                    </c:forEach>
            	</tbody>
            </table>
    </div>
	
    <div class="footer panel-footer">
        <p><a href="/">iFactura</a></p>
        <p>
            <a href="http://www.etsit.upm.es">ETSI Telecomunicación @ 2015</a>
            <a href="http://www.upm.es">Universidad Politécnica de Madrid</a>
        </p>
    </div>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
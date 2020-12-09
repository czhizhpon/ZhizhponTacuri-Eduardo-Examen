<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="/ZhizhponTacuri-Eduardo-Examen/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/ZhizhponTacuri-Eduardo-Examen/css/fontawesome/all.min.css">
    
	<script src="/ZhizhponTacuri-Eduardo-Examen/js/bootstrap/jquery-3.5.1.min.js"></script>
    <script src="/ZhizhponTacuri-Eduardo-Examen/js/bootstrap/popper.min.js"></script>
    <script src="/ZhizhponTacuri-Eduardo-Examen/js/bootstrap/bootstrap.min.js"></script>
    
    <script src="/ZhizhponTacuri-Eduardo-Examen/js/main.js"></script>
    <script src="/ZhizhponTacuri-Eduardo-Examen/js/register.js"></script>
    
    <title>Lista de Usuarios</title>
</head>
<body>

	<section class="container">
		<h2>Listado por número</h2>
		<div class="row justify-content-center">
			<form class="form-group row" onsubmit="searchByNumber(); return false;">
				<div class="col-10">
					<label for="phone.number">Número de teléfono</label>
					<input type="text" class="form-control" name="phone.number" id="phone.number" value="${s}"/>
				</div>
				<div class="col-1">
					<label for=""></label>
					<button type="button" class="btn btn-primary" onclick="searchByNumber();"><i class="fas fa-search"></i></button>
				</div>
			</form>
		</div>
		<div class="row table-responsive">
			<table class="table table-bordered" id="list">
				<thead>
      			<tr>
      				<th scope="col">Numero</th>
      				<th scope="col">Operadora</th>
			        <th scope="col">Tipo</th>
			        <th scope="col">Nombre</th>
			        <th scope="col">Apellido</th>
			        <th scope="col">Correo Elec.</th>
			        <th scope="col">Cédula</th>
      			</tr>
      			<c:forEach var="phone" items="${phones}">
      			<tr>
      				<td> ${phone.number} </td>
      				<td> ${phone.operator.name} </td>
      				<td> ${phone.phoneType.name} </td>
      				<td> ${phone.person.name} </td>
      				<td> ${phone.person.lastname} </td>
      				<td> ${phone.person.email} </td>
      				<td> ${phone.person.idCard} </td>
     			</tr>
   				</c:forEach>
			</table>
		</div>
	</section>

</body>
</html>
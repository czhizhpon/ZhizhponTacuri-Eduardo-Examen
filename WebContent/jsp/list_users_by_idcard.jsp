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
		<h2>Listado por Cédula</h2>
		<div class="row justify-content-center">
			<form class="form-group row" onsubmit="searchByIdCard(); return false;">
				<div class="col-10">
					<label for="person.idCard">Cédula</label>
					<input type="text" class="form-control" name="person.idCard" id="person.idCard" value="${s}"/>
				</div>
				<div class="col-1">
					<label for=""></label>
					<button type="button" class="btn btn-primary" onclick="searchByIdCard()"><i class="fas fa-search"></i></button>
				</div>
			</form>
		</div>
		<div class="row table-responsive">
			<table class="table table-bordered" id="list">
				<thead>
      			<tr>
      				<th scope="col">Cédula</th>
      				<th scope="col">Nombre</th>
			        <th scope="col">Apellido</th>
			        <th scope="col">Correo Elec.</th>
			        <th scope="col">Número Telf.</th>
			        <th scope="col">Operadora</th>
			        <th scope="col">Tipo</th>
      			</tr>
      			<c:forEach var="person" items="${people}">
      			<c:set var="n" scope="request" value="${person.phones.size()}"/>
      			<tr>
      				<td rowspan="${n}"> ${person.idCard} </td>
      				<td rowspan="${n}"> ${person.name} </td>
      				<td rowspan="${n}"> ${person.lastname} </td>
      				<td rowspan="${n}"> ${person.email} </td>
      				<c:set var="i" scope="request" value="${0}"/>
      				<c:forEach var="phone" items="${person.phones}">
      					<c:choose>
      					<c:when test="${i == 0}">
      					<td> ${phone.number}</td>
	      				<td> ${phone.operator.name}</td>
	      				<td> ${phone.phoneType.name}</td>
      					</c:when>
      					<c:otherwise>
  						<tr>
	      					<td> ${phone.number}</td>
		      				<td> ${phone.operator.name}</td>
		      				<td> ${phone.phoneType.name}</td>
						</tr>
      					</c:otherwise>
      					</c:choose>
      					<c:set var="i" scope="request" value="${i + 1}"/>
    				</c:forEach>
   				</c:forEach>
			</table>
		</div>
	</section>

</body>
</html>
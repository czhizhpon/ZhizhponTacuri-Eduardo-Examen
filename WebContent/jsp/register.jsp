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
    
    <title>Registro</title>
</head>
<body>
	<div class="container">
		<div id="notice" class="row m-4"></div>
		<div class="row">
			<div class="col-5">
				<div>
					<h2>Datos personales</h2>
				    <form id="user-form">
				    	<div class="form-group">
						    <label for="idCard">Cédula:</label>
						    <input type="text" name="idCard" id="idCard" class="form-control"/>
					    </div>
				    	<div class="form-group">
						    <label for="name">Nombre:</label>
						    <input type="text" name="name" id="name" class="form-control"/>
					    </div>
					    <div class="form-group">
						    <label for="lastname">Apellidos:</label>
						    <input type="text" name="lastname" id="lastname" class="form-control"/>
					    </div>
					    <div class="form-group">
						    <label for="email">Correo electrónico:</label>
						    <input type="email" name="email" id="email" class="form-control"/>
					    </div>
					    <div class="form-group">
						    <label for="password">Contraseña:</label>
						    <input type="password" name="password" id="password" class="form-control"/>
					    </div>
					    <div class="form-group row justify-content-end">
					    	<input type="button" class="btn btn-primary" value="Registrar" onclick="createPerson();"/>
					    </div>
				    </form>
			    </div>
		    </div>
		    <div class="col-7">
		    	<h2>Teléfonos</h2>
	    		<form class="row" id="phone-form">
	    			<div class="col-12">
	    			<div class="form-group row">
	    				<div class="col-4">
							<label for="number">Número:</label>
							<input type="text" class="form-control" id="number" name="number"/>
						</div>
	    				<div class="col-4">
			    			<label for="operator_id" class="">Operadora:</label>
							<select name="operator.id" id="operator_id" class="form-control">
								<c:forEach var="operator" items="${operators}">
					  				<option value="${operator.id}">${operator.name}</option>
					  			</c:forEach>
							</select>
						</div>
						<div class="col-4">
			    			<label for="phone_type_id" class="">Tipo:</label>
							<select name="phoneType.id" id="phone_type_id" class="form-control">
					  			<c:forEach var="pht" items="${phoneTypes}">
					  				<option value="${pht.id}">${pht.name}</option>
					  			</c:forEach>
							</select>
							<input type="hidden" name="person.id" value="0">
						</div>
					</div>
					<button type="button" id="create-phone" class="btn btn-outline-info btn-block" onclick="createPhone();" disabled> + </button>
					</div>
		    	</form>
		    	
		    	<div class="row">
	    		<div id="phones" class="col-12">
	    		</div>
	    	</div>
	    	</div>
	    </div>
    </div>
</body>
</html>
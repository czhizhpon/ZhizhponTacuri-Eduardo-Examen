/**
 * 
 */
var form_id = 0;
var person_id = 0;
function addPhoneForm(id){
	var operator_name = $("#operator_id :selected").text();
	
	var phone_type_name = $("#phone_type_id :selected").text();
	
	var number = document.getElementById("number").value;
	
	var html_form = `
		<form id="fph-${form_id}" class="row mt-4">
			<div class="col-4">
				<input type="text" value="${number}" class="form-control" disabled>
			</div>
			<div class="col-3">
				<input type="text" value="${operator_name}" class="form-control" disabled>
			</div>
			<div class="col-3">
				<input type="text" value="${phone_type_name}" class="form-control" disabled>
			</div>
			<div class="col-1">
				<button type="button" class="btn btn-danger" onclick="removePhoneForm('fph-${form_id}', ${id});"><i class="fas fa-trash"></i></button>
			</div>
		</form>
	`;
	var e = document.createElement('div');
	e.innerHTML = html_form;
	form_id = form_id + 1;
	var list = document.getElementById("phones");
	while(e.firstChild){
		list.appendChild(e.firstChild);
	}
}

function removePhoneForm(id, p_id){
	var url = "RemovePhone?phone_id=" + p_id
	$.get(url, function(res){
		var resp = res.split(":", 2);
		if(resp[0] == "notice"){
			document.getElementById("notice").innerHTML = resp[1];
		}else if(resp[0] == "success"){
			var list = document.getElementById("phones");
			var e = document.getElementById(id);
			list.removeChild(e);
			document.getElementById("notice").innerHTML = resp[1];
		} else {
			document.getElementById("notice").innerHTML = "Hubo un error al eliminar";
		}
	});
}

function createPerson(){
	var url = "/ZhizhponTacuri-Eduardo-Examen/CreatePerson"
	var form = $("#user-form");
	var formPhones = $('[id^="fph-"]');
	var nForm = formPhones.length;
	//if(nForm <= 0){
		//document.getElementById("notice").innerHTML = "Ingrese al menos un teléfono";
		//return false;
	//}
	$.post(url, form.serialize(), function(res){
		var resp = res.split(":", 2);
		if(resp[0] == "notice"){
			document.getElementById("notice").innerHTML = resp[1];
		}else if(resp[0] == "id"){
			person_id = parseInt(resp[1]);
			document.getElementById("create-phone").disabled = false;
		} else {
			document.getElementById("notice").innerHTML = "Hubo un error al crear";
		}
	});
}

function createPhone(){
	
	if (person_id === 0){
		return false;
	}
	
	var number = document.getElementById("number").value;
	
	if(number == ""){
		document.getElementById("notice").innerHTML = "Ingrese un número de teléfono";
		return false;
	}
	
	var formPhone = document.getElementById("phone-form");
	
	for(var j = 0; j < formPhone.elements.length; j++){
		if(formPhone.elements[j].name == "person.id"){
			formPhone.elements[j].value = person_id;
		}
	}
	var form = $("#phone-form");
	var url = "/ZhizhponTacuri-Eduardo-Examen/CreatePhone"
	console.log(form.serialize())
	$.post(url, form.serialize(), function(res){
		var resp = res.split(":", 2);
		if(resp[0] == "notice"){
			document.getElementById("notice").innerHTML = resp[1];
			return false;
		}else if(resp[0] == "success"){
			addPhoneForm(resp[1]);
		} else {
			document.getElementById("notice").innerHTML = "Hubo un error al crear teléfono";
		}
	});
}


/**
 * 
 */
var form_id = 0;
var person_id = 0;
function addPhoneForm(){
	var operator_name = $("#operator_id :selected").text();
	var operator_id = $("#operator_id :selected").val();
	
	var phone_type_name = $("#phone_type_id :selected").text();
	var phone_type_id = $("#phone_type_id :selected").val();
	
	var number = document.getElementById("number").value;
	
	if(number == ""){
		document.getElementById("notice").innerHTML = "Ingrese un número de teléfono";
		return false;
	}
	
	var html_form = `
		<form id="fph-${form_id}" class="row mt-4">
			<div class="col-4">
				<input type="text" value="${number}" class="form-control" disabled>
				<input type="hidden" name="number" value="${number}">
			</div>
			<div class="col-3">
				<input type="text" value="${operator_name}" class="form-control" disabled>
				<input type="hidden" name="operator.id" value="${operator_id}">
			</div>
			<div class="col-3">
				<input type="text" value="${phone_type_name}" class="form-control" disabled>
				<input type="hidden" name="phoneType.id" value="${phone_type_id}">
				<input type="hidden" name="person.id" value="0">
			</div>
			<div class="col-1">
				<button type="button" class="btn btn-danger" onclick="removePhoneForm('fph-${form_id}');"><i class="fas fa-trash"></i></button>
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

function removePhoneForm(id){
	var list = document.getElementById("phones");
	var e = document.getElementById(id);
	list.removeChild(e);
}

function createPerson(){
	var url = "/ZhizhponTacuri-Eduardo-Examen/CreatePerson"
	var form = $("#user-form");
	var formPhones = $('[id^="fph-"]');
	var nForm = formPhones.length;
	if(nForm <= 0){
		document.getElementById("notice").innerHTML = "Ingrese al menos un teléfono";
		return false;
	}
	$.post(url, form.serialize(), function(res){
		var resp = res.split(":", 2);
		console.log(resp);
		if(resp[0] == "notice"){
			document.getElementById("notice").innerHTML = resp[1];
		}else if(resp[0] == "id"){
			person_id = parseInt(resp[1]);
			createPhone(formPhones);
		} else {
			document.getElementById("notice").innerHTML = "Hubo un error al crear";
		}
	});
}

function createPhone(formPhones){
	if (person_id === 0){
		return false;
	}
	var nForm = formPhones.length;
	for(var i = 0; i < nForm; i++){
		var e = formPhones[i];
		for(var j = 0; j < e.elements.length; j++){
			if(e.elements[j].name == "person.id"){
				e.elements[j].value = person_id;
			}
		}
		var form = $("#" + e.id);
		console.log(form)
		var url = "/ZhizhponTacuri-Eduardo-Examen/CreatePhone"
		$.post(url, form.serialize(), function(res){
			if(resp[0] == "notice"){
				document.getElementById("notice").innerHTML = resp[1];
				return false;
			}else if(resp[0] == "success"){
			} else {
				document.getElementById("notice").innerHTML = "Hubo un error al crear teléfono";
			}
		});
	}
}


/**
 * 
 */

function createOperators(){
	var url = "CreateOperator"
	$.get(url, function(res){
		document.getElementById("notice").innerHTML = res;
	});
}

function createPhoneType(){
	var url = "CreatePhoneType"
	$.get(url, function(res){
		document.getElementById("notice").innerHTML = res;
	});
}

function searchByNumber(){
	var s = document.getElementById("phone.number").value;
	location.href = "ListUsersByPhone?s=" + s;
}

function searchByIdCard(){
	var s = document.getElementById("person.idCard").value;
	location.href = "ListPersonByIdCard?s=" + s;
}
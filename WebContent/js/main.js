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
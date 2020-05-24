// Parte perfil de usuario
var editarPassword;
var editar;
var buttonGuardar;
window.onload = ()=>{
	editarPassword = document.getElementById("UserPass");
	editar = document.getElementById("editarUserPass");
	buttonGuardar = document.getElementById("guardarDatosPersonales");
	if(editarPassword != null && editar && buttonGuardar){
		editarPassword.setAttribute("disabled", true);
		editarPassword.style.color ="gray";
		console.log(editar);
		console.log(editarPassword);
		editar.addEventListener('click', modDisabled);
		editarPassword.addEventListener('focus', onFocus);
		editarPassword.addEventListener('change', modDisabled);
		//buttonGuardar.addEventListener('click', modDisabled);
		//buttonGuardar.addEventListener('click', window.location.reload(false));
		

	} else {
	 	editarPassword = "";
		editar = "";
		buttonGuardar = "";
	}
	
	
}

function modDisabled(){
	if(editarPassword.disabled){
	editarPassword.disabled = false;
	editarPassword.removeAttribute("disabled");
	
	} else {
		editarPassword.setAttribute("disabled", true);
		
	}
	
	if(editarPassword.value === ""){
		editarPassword.value = "*******************";
		editarPassword.style.color ="gray";
	}

}

function onFocus(){
	if(editarPassword.disabled===false){
	editarPassword.style.color ="#000";
	editarPassword.value="";
	}
	
}

// Fin // Parte perfil de usuario

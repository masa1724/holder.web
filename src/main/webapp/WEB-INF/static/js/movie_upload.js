function checkInput() {
	var name = document.getElementById("name").value;
	var description = document.getElementById("description").value;
	
	if(name.length == 0) {
		document.getElementById("name");
		return;
	}
	
	if(description.length == 0) {
		document.getElementById("name");
		return;
	}
}


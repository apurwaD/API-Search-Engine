function onSelect() {
	var criteria = document.getElementById("api");
	var select = document.getElementById("searchType").value;
	if (select == "apis") {
		document.getElementById("api").style.display = "inline";
		document.getElementById("mashup").style.display = "none";
	} else {

		if (select == "mashup") {
			document.getElementById("api").style.display = "none";
			document.getElementById("mashup").style.display = "inline";
		} else {
			document.getElementById("api").style.display = "none";
			document.getElementById("mashup").style.display = "none";
		}
	}

}

function validate() {

	var radios = myForm.elements["criteria"];

	for (var i = 0, len = radios.length; i < len; i++) {
		if (radios[i].checked) {
			var value = radios[i].value;

			if (value == "rating" || value == "updatedyear") {
				var search = document.myForm.searchvalue.value;

				if (isNaN(search)) {
					alert("Please enter numeric value");
					return false;
				}
			}
		}
	}

}
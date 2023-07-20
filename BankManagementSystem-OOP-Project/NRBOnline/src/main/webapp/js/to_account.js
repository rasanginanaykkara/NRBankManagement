function ValidationEvent() {
	var faccount = document.getElementById("faccount").value;
	var taccount = document.getElementById("taccount").value; 
	
	if(faccount.length != 7){
		alert("Invalid From Account Number,Please Re-Enter!");
		return false;
	}else if(taccount.length != 7){
		alert("Invalid To Account Number,Please Re-Enter!");
		return false;
	}
 }

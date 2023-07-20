function ValidationEvent() {
	var faccount = document.getElementById("faccount").value;
	var lnumber = document.getElementById("lnumber").value; 
	
	if(faccount.length != 7){
		alert("Invalid From Account Number,Please Re-Enter!");
		return false;
	}else if(lnumber.length != 6){
		alert("Invalid Loan Number,Please Re-Enter!");
		return false;
	}
 }

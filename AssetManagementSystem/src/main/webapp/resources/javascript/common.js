function deleteRow(table, index) {
	 document.getElementById(table).deleteRow(index);
}
function openDialogue(url) {
	 var left = (screen.width/2)-(600/2);
	  var top = (screen.height/2)-(700/2);
	  window.open(url, "_blank", "scrollbars=yes,width=600,height=700", top='+top+', left='+left+');
}
function clearText(textcd, text_name) {
	document.getElementById(textcd).value="";
	document.getElementById(text_name).value="";
	
}
function ToLink(url)
{
	window.location.href=url;
}
function formatMoney(amount, decimalCount = 0, decimal = ".", thousands = ".") {
	  try {
	    decimalCount = Math.abs(decimalCount);
	    decimalCount = isNaN(decimalCount) ? 2 : decimalCount;

	    const negativeSign = amount < 0 ? "-" : "";

	    let i = parseInt(amount = Math.abs(Number(amount) || 0).toFixed(decimalCount)).toString();
	    let j = (i.length > 3) ? i.length % 3 : 0;

	    return negativeSign + (j ? i.substr(0, j) + thousands : '') + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousands) + (decimalCount ? decimal + Math.abs(amount - i).toFixed(decimalCount).slice(2) : "");
	  } catch (e) {
	    console.log(e)
	  }
	};

//hàm chọn dữ liệu
function GetSelected() {
	//Reference the Table.
	var grid = document.getElementById("table.data");

	//Reference the CheckBoxes in Table.
	var checkBoxes = document.getElementsByName("checkboxrow");

	//Loop through the CheckBoxes.
	for (var i = 0; i < checkBoxes.length; i++) {
		var color = "white";
		if (checkBoxes[i].checked) {
			color = "yellow";
		}

		var row = checkBoxes[i].parentNode.parentNode;
		for (var j = 0; j < row.cells.length; j++) {
			row.cells[j].style.backgroundColor = color;
		}
	}

}
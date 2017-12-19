function submitData(){
       
	var notice = {
			modality: { modalityId: 1 },
			number: $("#number").val(),
			object: $("#object").val(),
			tradingDate: '2017-01-3',
			url: 'www.uol.com.br'
	}
	
	$.ajax({
	   type: "post",
	   dataType: "json",
	   url: "http://localhost:8080/DivulgaEditais/rest/notice/create",
	   // The key needs to match your method's input parameter (case-sensitive).
	   data: JSON.stringify( notice ),
	   processData: false,
	   contentType: 'application/json',
	   success: function(data){alert('Sucesso:' + data);},
	   failure: function(errMsg) {
	       alert('Erro:' + errMsg);
	   }
	});

function validation() {
  $("form[name='registerNotice']").validate({
    rules: {
        modality: "required",
        number: "required",
        object: "required",
        trading_date: "required",
        //file: "required",
    }})
  }
}

function Upload()
{
    var file = document.getElementById('file');

    if(file.files.length)
    {
        var reader = new FileReader();

        reader.onload = function(e)
        {
        	var fileContent = e.target.result;
        	console.log(fileContent);
            //document.getElementById('outputDiv').innerHTML = e.target.result;
        };

        reader.readAsBinaryString(file.files[0]);
    }
}
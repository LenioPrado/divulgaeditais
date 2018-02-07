window.onload = function() {
	loadModalities();
};

function loadModalities(){
	
	var url = window.location.href;
	var arr = url.split("/");
	var result = arr[0] + "//" + arr[2];
	
	$.getJSON('/DivulgaEditais/rest/modality', function(json) {
		$select = $('#modality');
		$.each(json, function(i, value) {
	           $select.append('<option value="' + value.modalityId + '">' + value.acronyms + ' - ' +value.description + '</option>');
	    });
	});
}

function submitData(){
       
	modalityId =  $('#modality').val();
	alert('Modalidade: ' + modalityId);

	var notice = {
			modality: { modalityId: modalityId },
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
	   processData: true,
	   contentType: 'application/json',
	   success: function(data){
		   alert('Enviado:' + data);
	   },
	   failure: function(errMsg) {
	       alert('Erro:' + errMsg);
	   }
	});
	
}

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
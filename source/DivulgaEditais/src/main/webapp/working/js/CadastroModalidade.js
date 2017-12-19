function validation() {
  $("form[name='registerModality']").validate({
            rules: {
                acronyms: "required",
                description: "required",
            },

            messages: {
                acronyms: "Digite um acronimo valido",
                description: "Digite uma Descrição valido",
            },

    submitHandler: function(form) {
        alert("Chegou no Submit");
        submit();
    }
  });

function submit(){

	var modality = {
			acronyms: "PRExy",
			description: "Tomada de Preço"
	}
	
	$.ajax({
	   type: "post",
	   dataType: "json",
	   url: "/DivulgaEditais/rest/modality/create",
	   // The key needs to match your method's input parameter (case-sensitive).
	   data: JSON.stringify( modality ),
	   processData: false,
	   contentType: 'application/json',
	   success: function(data){alert('Sucesso:' + data);},
	   failure: function(errMsg) {
	       alert('Erro:' + errMsg);
	   }
	});
    }
}
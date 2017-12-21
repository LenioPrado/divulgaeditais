window.onload = function() {
	loadProperties();
};  

function loadProperties(){
	
	$("#tableNotice").hide();
	$("#register").hide();
	$.getJSON('modality.json', function(json) {
		  $select = $('#modality');
		  $.each(json, function(i, value) {
		    //<option value="townhall">Usuário da Prefeitura</option>
		           $select.append('<option id="' + value.acronyms+ '">' + value.description + '</option>');
		        });
		    console.log(json); // this will show the info it in firebug console
		});
}

function sendNotice(number){
          console.log(number);
          
          $.ajax({
                    url  : 'teste.php',
                    type : 'POST',
                    data: number
         });
      }
      
function fillAllNotice(){
  console.log("Entrou na funcao");

        $.getJSON("notice.json", function(json) {
            $("#notices tbody tr").remove();       
            $.each(json, function(i, value) {
          $('#notices > tbody:last-child').append('<tr><td>' + value.modality+ '</td><td>' + value.number + '</td><td>' + value.object + '</td><td>' + value.trading_date + '</td><td><a id="open" href="Editais/'+value.number+'.pdf"> Abrir Edital </a></td></tr>');
           //onClcick="sendNotice('+value.number+')"
           console.log(value);
        }); 
    }); 

  }

function loadRegister(){
    $("#tableNotice").hide();
    $("#register").show();
  }

function loadTable(){
    $("#register").hide();
    $("#tableNotice").show();
    fillAllNotice();

  }

function validation() {
	  $("form[name='registerNotice']").validate({
	            rules: {
	               modality: "required",
	                number: "required",
	                object: "required",
	                trading_date: "required",
	                //file: "required",
	            },

	            messages: {
	                modality: "Escolha uma modalidade válida",
	                number: "Digite um número válido",
	                object: "Digite uma descrição válida",
	                trading_date: "Selecione uma data válida",
	                //file: "Insira um arquivo valido",
	            },
	            errorClass: "registerError",
	            validClass: "registerSuccess",

	    submitHandler: function(form) {
	        saveFile();
	        alert("Edital cadastrado com sucesso!");
	        location.reload(this);
	    }
	  });
	      function saveFile()
	      {
	         $('#registerNotice').ajaxSubmit({
	            url  : 'writeNotice.php',
	            type : 'POST'
	         });
	      }
}

function fillRegisteredNotice(){
	  console.log("Entrou na funcao");
	$.getJSON('registeredNotice.json', function(json) {
	  console.log("pegou o json dos editais cadastrados");
	  $("#notices tbody tr").remove();
	  $.each(json, function(i, value) {
	          $('#notices > tbody:last-child').append('<tr><td>' + value.modality+ '</td><td>' + value.number + '</td><td>' + value.object + '</td><td>' + value.trading_date + '</td><td>' + value.url + '</td><td><button id="open" onclick="sendNotice('+value.number+')"> Abrir Edital </button></td></tr>');
	           // onClcick="sendNotice('+value.number+')"
	           console.log(value);
	        });
	    console.log(json); // this will show the info it in firebug console
	});
	}
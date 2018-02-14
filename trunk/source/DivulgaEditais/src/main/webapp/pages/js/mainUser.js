window.onload = function() {
	loadProperties();
};  

function loadProperties(){
	
	$("#tableNotice").hide();
	$("#register").hide();
	
	var baseUrl = getServerUrl();
	
	$.getJSON(baseUrl + '/DivulgaEditais/rest/modality', function(json) {
		  $select = $('#modality');
		  $.each(json, function(i, value) {
			  $select.append('<option value="' + value.modalityId + '">' + value.acronyms + ' - ' + value.description + '</option>');
		        });
		    console.log(json);
		});
	
	$.getJSON(baseUrl + '/DivulgaEditais/rest/companyTypes', function(json) {
		  $select = $('#company_type');
		  $.each(json, function(i, value) {
			  $select.append('<option value="' + value.companyTypeId + '">' + value.acronyms + ' - ' + value.description + '</option>');
		        });
		    console.log(json);
		});
	
	$.getJSON(baseUrl + '/DivulgaEditais/rest/categories', function(json) {
		  $select = $('#categories');
		  $.each(json, function(i, value) {
			  $select.append('<option value="' + value.categoryId + '">' + value.description + '</option>');
		        });
		    console.log(json);
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
  
	var baseUrl = getServerUrl();
	
	$.getJSON(baseUrl + '/DivulgaEditais/rest/notice', function(json) {  
        $("#notices tbody tr").remove();       
        $.each(json, function(i, value) {
        	
        	var date = new Date(value.tradingDate);
        	date.setTime(date.getTime() + date.getTimezoneOffset()*60*1000);
        	var tradingDate = date.toLocaleDateString("pt-BR");
        	
        	var date = new Date(value.publishingDate);
        	date.setTime(date.getTime() + date.getTimezoneOffset()*60*1000);
        	var publishingDate = date.toLocaleDateString("pt-BR");
        	
        	var date = new Date(value.closingDate);
        	date.setTime(date.getTime() + date.getTimezoneOffset()*60*1000);
        	var closingDate = date.toLocaleDateString("pt-BR");
        	
          $('#notices > tbody:last-child').append(
        		  '<tr><td>' + 
        		  value.companyType + '</td><td>' + 
        		  value.modality.acronyms + '-' + value.modality.description + '</td><td>' + 
        		  value.number + '</td><td>' + 
        		  value.object + '</td><td>' + 
        		  publishingDate + '</td><td>' +
        		  tradingDate + '</td><td>' +
        		  closingDate + '</td><td>' +
        		  '</td><td><a id="open" href="Editais/' + 
        		  value.fileName +'.pdf"> Abrir Edital </a></td></tr>'
        		  );
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

function fillRegisteredCategories(){
    $("#register").hide();
    $("#tableNotice").show();
    fillAllNotice();

  }

function loadCategories(){
    $("#register").hide();
    $("#tableNotice").show();
    fillAllNotice();

  }

function fillRegisteredModalities(){
    $("#register").hide();
    $("#tableNotice").show();
    fillAllNotice();

  }

function loadModalities(){
    $("#register").hide();
    $("#tableNotice").show();
    fillAllNotice();

  }

function fillRegisteredCompanyTypes(){
    $("#register").hide();
    $("#tableNotice").show();
    fillAllNotice();

  }

function loadCompanyTypes(){
    $("#register").hide();
    $("#tableNotice").show();
    fillAllNotice();

  }



function validation() {
	  $("form[name='registerNotice']").validate({
	            rules: {
	            	companyType: "required",
	            	modality: "required",
	                number: "required",
	                object: "required",
	                trading_date: "required",
	                closingDate: "required",
	                categories: "required",
	                fileName: "required",
	                //file: "required",
	            },

	            messages: {
	            	companyType: "Selecione um tipo válido",
	                modality: "Escolha uma modalidade válida",
	                number: "Digite um número válido",
	                object: "Digite uma descrição válida",
	                trading_date: "Selecione uma data válida",
	                closingDate: "Selecione uma data válida",
	                categories: "Selecione uma categoria válida",
	                fileName: "Insira um arquivo válido",
	            },
	            errorClass: "registerError",
	            validClass: "registerSuccess",

	    submitHandler: function(form) {
	    	submitData();    	
	    	//saveFile();
	        location.reload(this);
	    }
	  });
}

function submitData(){
	
	var modalityId = $('#modality').val();
	alert('Modalidade: ' + modalityId);

	var notice = getFormData('#registerNotice');
	
	var baseUrl = getServerUrl();
	
	$.ajax({
	   type: "post",
	   dataType: "json",
	   url: getServerUrl() + "/DivulgaEditais/rest/notice/create",
	   // The key needs to match your method's input parameter (case-sensitive).
	   data: JSON.stringify( notice ),
	   processData: false,
	   contentType: 'application/json',
	   success: function(data){
		   alert("Edital cadastrado com sucesso!");
	   },
	   failure: function(errMsg) {
	       alert('Erro:' + errMsg);
	   }
	});
}

function fillRegisteredNotice(){
	  console.log("Entrou na funcao");
	$.getJSON('registeredNotice.json', function(json) {
	  console.log("pegou o json dos editais cadastrados");
	  $("#notices tbody tr").remove();
	  $.each(json, function(i, value) {
	          $('#notices > tbody:last-child').append(
	        		  '<tr><td>' + value.modality+ '</td><td>' + 
	        		  value.number + '</td><td>' + value.object + 
	        		  '</td><td>' + value.trading_date + '</td><td>' + 
	        		  value.url + '</td><td><button id="open" onclick="sendNotice('+value.number+')">Abrir Edital </button></td></tr>');
	           console.log(value);
	        });
	    console.log(json);
	});
	}
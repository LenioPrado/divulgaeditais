function validateNotice(callback) {
	  $("form[name='registerNotice']").validate({
            rules: {
            	companyType: "required",
            	modality: "required",
                number: "required",
                object: "required",
                tradingDate: "required",
                closingDate: "required",
                categories: "required",
                fileName: "required"
            },

            messages: {
            	companyType: "Selecione um tipo válido",
                modality: "Escolha uma modalidade válida",
                number: "Digite um número válido",
                object: "Digite uma descrição válida",
                tradingDate: "Selecione uma data válida",
                closingDate: "Selecione uma data válida",
                categories: "Selecione uma categoria válida",
                fileName: "Insira um arquivo válido",
            },
            errorClass: "registerError",
            validClass: "registerSuccess",

	    submitHandler: function(form) {
	    	callback(form); 
	    }
	  });
}

function validateCategory(callback){
	  $("form[name='registerCategory']").validate({
		  rules: {
			  description: "required",
		  },
		
		  messages: {
		      description: "Digite uma categoria Válida",
		  },
		  errorClass: "registerError",
		  validClass: "registerSuccess",
		
		  submitHandler: function(form) {
			  callback(form);					
		  }
	  });
}

function validateModality(callback){
	  $("form[name='registerModality']").validate({
        rules: {
        	acronyms: "required",
        	description: "required",
        },

        messages: {
          	acronyms: "Digite um Acronimo Válido",
            description: "Digite uma Descrição Válida",
        },
        errorClass: "registerError",
        validClass: "registerSuccess",

        submitHandler: function(form) {
        	callback(form);
        }
	  });
}

function validateCompanyType(callback){
	  $("form[name='registerCompanyType']").validate({
	      	rules: {
				acronyms: "required",
				description: "required",
	      	},
	
	      messages: {
	        	acronyms: "Digite um Acronimo Válido",
	          	description: "Digite uma Descrição Válida",
	      },
	      errorClass: "registerError",
	      validClass: "registerSuccess",
	
	      submitHandler: function(form) {
	    	  callback(form);
	      }
	  });
}
function registerNotice(){	
	var notice = getFormData('registerNotice');

	notice['companyType'] = { 'companyTypeId' : notice['companyType'] };
	notice['modality'] = { 'modalityId' : notice['modality'] };
	notice['user'] = { 'userId' : notice['user'] };
	notice['fileName'] = cleanString(notice['fileName'].split('\\').pop());
	
	var noticesCategories = notice['noticesCategories'];
	notice['noticesCategories'] = [];
	$.each(noticesCategories, function(i, value){
		notice['noticesCategories'].push({'category': {'categoryId' : value}});		
	});
	
	var file = $('#fileName').get(0).files[0];
	var cleanFileName = cleanString(file.name);
	
	var formData = new FormData();
	formData.append('uploadfile', file, cleanFileName);
	formData.append('notice', JSON.stringify( notice ));
	 
	var bar = $('.bar');
	var percent = $('.percent');
	var status = $('#status');
	
	$.ajax({
		url: getServerUrl() + "/DivulgaEditais/rest/file",
		type: 'POST',
		xhr: function() {
			var myXhr = $.ajaxSettings.xhr();
		    if (myXhr.upload) { // Avalia se tem suporte a propriedade upload
		        myXhr.upload.addEventListener('progress', function (evt) {
		        	var percentComplete = Math.ceil((evt.loaded / evt.total) * 100);
			        var percentVal = percentComplete + '%';
			        bar.width(percentVal)
			        percent.html(percentVal);
		            
		        }, false);
		    }
		    return myXhr;
		},
		// beforeSend: beforeSendHandler,
		success: function(data) {
			showMessage('Edital cadastrado com sucesso.','success');
		},
		error: function(jqXHR, exception) {
			var msg = jqXHR.responseText;
			showMessage(msg,'error');
		},
		data: formData,
		cache: false,
		contentType: false,
		processData: false
	});
}

function registerCategory(){
	registerEntity('registerCategory', '/DivulgaEditais/rest/category/create', 'Categoria cadastrada com sucesso.');	
}

function registerModality(){
	registerEntity('registerModality', '/DivulgaEditais/rest/modality/create', 'Modalidade cadastrada com sucesso.');
}

function registerCompanyType(){
	registerEntity('registerCompanyType', '/DivulgaEditais/rest/companyTypes/create', 'Tipo de Empresa cadastrado com sucesso.');
}

function registerEntity(formName, urlToRegister, successMessage){
	var entity = getFormData(formName);
	
	$.ajax({
	   type: "post",
	   dataType: "json",
	   url: getServerUrl() + urlToRegister,
	   data: JSON.stringify( entity ),
	   processData: false,
	   contentType: 'application/json',
	   success: function(data){
		   showMessage(data,'success');
	   },
	   error: function(jqXHR, exception) {
			var msg = jqXHR.responseText;
			showMessage(msg,'error');
	   }
	});	
}
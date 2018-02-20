function registerNotice(){	
	var notice = getFormData('registerNotice');

	notice['companyType'] = { 'companyTypeId' : notice['companyType'] };
	notice['modality'] = { 'modalityId' : notice['modality'] };
	notice['user'] = { 'userId' : notice['user'] };
	notice['fileName'] = $('#fileName').val().split('\\').pop();
	
	console.log(notice);
	
	$.ajax({
	   type: "post",
	   dataType: "json",
	   url: getServerUrl() + "/DivulgaEditais/rest/notice/create",
	   data: JSON.stringify( notice ),
	   processData: true,
	   contentType: 'application/json',
	   success: function(data){
		   sendFile(data.noticeId);		   
	   },
	   failure: function(errMsg) {
	       alert('Erro:' + errMsg);
	   }
	});
}

function registerModality(){
	var modality = getFormData('registerModality');	
	
	$.ajax({
	   type: "post",
	   dataType: "json",
	   url: getServerUrl() + "/DivulgaEditais/rest/modality/create",
	   data: JSON.stringify( modality ),
	   processData: false,
	   contentType: 'application/json',
	   success: function(data){
		   alert("Modalidade cadastrado com sucesso!");
	   },
	   failure: function(errMsg) {
	       alert('Erro:' + errMsg);
	   }
	});
}

function registerCompanyType(){
	var companyType = getFormData('registerCompanyType');
	
	$.ajax({
	   type: "post",
	   dataType: "json",
	   url: getServerUrl() + "/DivulgaEditais/rest/companyTypes/create",
	   data: JSON.stringify( companyType ),
	   processData: false,
	   contentType: 'application/json',
	   success: function(data){
		   alert("Tipo de Empresa cadastrado com sucesso!");
	   },
	   failure: function(errMsg) {
	       alert('Erro:' + errMsg);
	   }
	});
  
  location.reload(this);	
}

function sendFile(noticeId){
	
	var file = $('#fileName').get(0).files[0];
	var formData = new FormData();
	formData.append('uploadfile', file);
	formData.append('noticeId', noticeId);
 
  $.ajax({
      url: getServerUrl() + "/DivulgaEditais/rest/file",
      type: 'POST',
	  xhr: function() {
        var myXhr = $.ajaxSettings.xhr();
        if (myXhr.upload) { // Avalia se tem suporte a propriedade upload
            myXhr.upload.addEventListener('progress', function () {
                /* faz alguma coisa durante o progresso do upload */
            }, false);
        }
        return myXhr;
	  },
	  // beforeSend: beforeSendHandler,
	  success: function(data) {
		  alert('Arquivo enviado com sucesso com '+data+' KB.');
	  },
	  data: formData,
	  cache: false,
	  contentType: false,
	  processData: false
  });		
}
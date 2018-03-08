var separator = ' - ';

function getServerUrl(){
	var url = window.location.href;
	var arr = url.split("/");
	var result = arr[0] + "//" + arr[2] + "/DivulgaEditais/rest/";
	
	return result;
}

function showMessage(message, type){
	var className = "";
	var strongMsg = "";
	var divMessage = $('#messages');
	divMessage.show();

	if(type === 'success'){
		className = 'alert-success';
		strongMsg = "Sucesso!";
	} else if(type === 'error'){
		className = 'alert-danger';
		strongMsg = "Erro!";
	} else if(type === 'warning'){
		className = 'alert-warning';
		strongMsg = "Aviso!";
	}
	
	divMessage.contents().filter(function (event) {
	     return this.nodeType === 3; 
	}).remove();
	
	divMessage.removeClass("alert-success alert-danger alert-warning");
	divMessage.addClass(className);
	
	divMessage.find('strong').html(strongMsg);	
	divMessage.find('strong').after(' '+message);
}

function hideMessage(){
	var divMessage = $('#messages');
	$(divMessage).hide();	
}

function setFormData(object){
	
	$.each(object, function(field, value){
		if(value && value.constructor && value.constructor == Object){
			value = value[field+'Id'];
		}		
		var input = $('#'+field);
		if(input.length > 0 && input[0].type != 'file'){
			if(input[0].type === 'date'){
				var date = convertFormDateToSqlDate(value);
				$(input).val(date);
			} else if(input[0].type === 'number') {				
				$(input).val(parseInt(value));
			} else {
				$(input).val(value);
			}
		}
	});
}

function setSelectMultipleValues(entity, entityRelatedData){
	var entityValues = entity['noticesCategories'];
	var idFields = entityRelatedData['select-multiple-entity-id'];
	var select = $('#'+entityRelatedData['select-multiple-id']);
	var values = [];
	$.each(entityValues, function(i, value){
		var current = value;
		$.each(idFields, function(i, idField){
			if(current[idField]){
				current = current[idField];
			}
		});
		values.push(current);
	});
	select.val(values);
}

function getFormData(formName){
	
	var data = {};
	$("form[name='"+formName+"']").each(function(){		
	    var inputs = $(this).find(':input');
	    $.each(inputs, function(i, input){
	    	if(input.id && input.value && input.type != 'submit'){
	    		if(input.type === 'select-multiple'){
	    			data[input.id] = $(input).val();
	    		} else {
	    			data[input.id] = input.value;
	    		}	    		
	    	}	    		
	    })	    
	});
    
    return data;
}

function getEntity(methodUrl, callback){
	$.getJSON(getServerUrl() + methodUrl, function(json){
		console.log(json);
		callback(json);
	}).fail(function(jqXHR, textStatus, errorThrown) { 
		if(jqXHR.status === 401){
			showMessage('Você não possui acesso a esta página','error');
		} else {
			showMessage('Ocorreu um erro!','error');
		}		
	});
}

function fillSelect(json, selectId, valueFields, textFields){
	select = $('#'+selectId);		
	
	$.each(json, function(i, jsonValue) {
		var valueStr=getTextByObjectFields(jsonValue, valueFields);
		var textStr=getTextByObjectFields(jsonValue, textFields);
		
		var o = new Option(textStr,valueStr);
		select.append(o);
	});	
}

function getTableData(data, tableFields){
	var str="";
	$.each(tableFields, function(i, fields){
		
		if(!$.isArray(fields)){
			str+='<td>'+ getNestedEntityData(fields, data) + '</td>';			
		} else {
			str+='<td>';
			$.each(fields, function(j, field){
				if(j>0){
					str+=separator;
				}				
				str+=getNestedEntityData(field, data);
			})
			str+='</td>'
		}
	});
	
	return str; 
}

function getNestedEntityData(field, data){
	var entityFields = field.split('.');
	var myEntity = data;
	$.each(entityFields, function(k, entityField){
		myEntity = myEntity[entityField];
	})
	return myEntity;
}

function getTextByObjectFields(object, fields){
	var str="";
	if(!$.isArray(fields)){
		str=object[fields];		
	} else {
		$.each(fields, function(i, fieldName) {
			if(i>0){
				str+=separator;
			}
			str+=object[fieldName];
		});
	}
	return str;
}

function convertSqlDateToFormDate(sqlDateValue){
	var date = new Date(sqlDateValue);
	date.setTime(date.getTime() + date.getTimezoneOffset()*60*1000);
	return date.toLocaleDateString("pt-BR");	
}

function convertFormDateToSqlDate(dateValue){
	var date = dateValue.split('/');
	
	if(date.length > 1){
		var dateText = date[2]+'-'+date[1]+'-'+date[0];
		return dateText;
	} else {
		return dateValue;
	}
}

function loadDeleteConfirmModal(urlToGetData, processCallback, beforeProcessCallback){
	loadConfirmModal(urlToGetData, processCallback, beforeProcessCallback, 'Excluir Registro', 'Confirma exclusão do registro?');	
}

function loadConfirmModal(urlToGetData, processCallback, beforeProcessCallback, modalTitle, modalText){
	$('#confirmModal').load('modal.html', function(){
		$('#confirmAction').on('show.bs.modal', function (e) {
			
			$('#modalTitle').html(modalTitle);
			$('#modalText').html(modalText);
			
			var entityId = $(e.relatedTarget).attr('entity-data');
			var entity = $('#tr'+entityId).data('key');		      
		      
		      $(this).find('.modal-footer #confirm').off('click').on('click', function(){		    	  
		    	  processCallback(urlToGetData, entity, entityId, beforeProcessCallback);
		    	  $('#confirmAction').modal('toggle');
		      });		      
		  });			
	});
}

function cleanString(input) {
    var output = "";
    for (var i=0; i<input.length; i++) {
        if (input.charCodeAt(i) <= 127) {
            output += input.charAt(i);
        }
    }
    return output;
}

function isLogged(){
	$.getJSON(getServerUrl() + 'user/roles', function(json){

	}).fail(function(jqXHR, textStatus, errorThrown) { 
		if(jqXHR.status === 401){
			window.location.replace("index.html");
		} 		
	});	
}

function logout(){

	var urlToGetData = "user/logout";
	
	$.ajax({
		   type: "post",
		   dataType: "text",
		   url: getServerUrl() + urlToGetData,
		   processData: false,
		   success: function(){
			   window.location.replace("index.html");
		   },
		   error: function(jqXHR, exception) {
				var msg = jqXHR.responseText;
				showMessage(msg,'error');
				window.scrollTo(0, 0);
		   }
	});
}

function downloadFile(url, params){
	var xhr = new XMLHttpRequest();
	xhr.open('GET', getServerUrl() + url, true);
	xhr.responseType = 'arraybuffer';
	xhr.onload = function () {
		if (this.status != 200) {
			alert(this.status);
		} else if (this.status === 200) {
	        var filename = "";
	        var disposition = xhr.getResponseHeader('Content-Disposition');
	        if (disposition && disposition.indexOf('attachment') !== -1) {
	            var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
	            var matches = filenameRegex.exec(disposition);
	            if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');
	        }
	        var type = xhr.getResponseHeader('Content-Type');

	        var blob = typeof File === 'function'
	            ? new File([this.response], filename, { type: type })
	            : new Blob([this.response], { type: type });
	        if (typeof window.navigator.msSaveBlob !== 'undefined') {
	            // IE workaround for "HTML7007: One or more blob URLs were revoked by closing the blob for which they were created. These URLs will no longer resolve as the data backing the URL has been freed."
	            window.navigator.msSaveBlob(blob, filename);
	        } else {
	            var URL = window.URL || window.webkitURL;
	            var downloadUrl = URL.createObjectURL(blob);

	            if (filename) {
	                // use HTML5 a[download] attribute to specify filename
	                var a = document.createElement("a");
	                // safari doesn't support this yet
	                if (typeof a.download === 'undefined') {
	                    window.location = downloadUrl;
	                } else {
	                    a.href = downloadUrl;
	                    a.download = filename;
	                    document.body.appendChild(a);
	                    a.click();
	                }
	            } else {
	                window.location = downloadUrl;
	            }

	            setTimeout(function () { URL.revokeObjectURL(downloadUrl); }, 100); // cleanup
	        }
	    }
	};
	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	if(params){
		xhr.send($.param(params));	
	} else {
		xhr.send();	
	}
}
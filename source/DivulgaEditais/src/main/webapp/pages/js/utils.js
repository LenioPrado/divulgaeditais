var separator = ' - ';

function getServerUrl(){
	var url = window.location.href;
	var arr = url.split("/");
	var result = arr[0] + "//" + arr[2];
	
	return result;
}

function getFormData(formName){
    var splittedFormData = $("form[name='"+formName+"']").serialize().split('&');

    var data = {};
    $.each(splittedFormData, function (key, value) {
        var splittedValue = value.split('=');
        data[splittedValue[0]] = splittedValue[1];
    });
    
    return data;
}

function getEntity(methodUrl, callback){
	$.getJSON(getServerUrl() + methodUrl, function(json){
		console.log(json);
		callback(json);
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

function getFormatedDate(sqlDataValue){
	var date = new Date(sqlDataValue);
	date.setTime(date.getTime() + date.getTimezoneOffset()*60*1000);
	return date.toLocaleDateString("pt-BR");	
}
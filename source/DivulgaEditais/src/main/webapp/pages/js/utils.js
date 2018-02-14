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
function listNoticesRegisteredByUser(){
	listNotices(4);
}

function listNotices(userId){
	
	var fieldsArray = ['user.fantasyName', ['modality.acronyms','modality.description'], 
		'companyType.description','number', 'object', 
		'publishingDate', 'tradingDate', 'closingDate','edital'];
	
	var url="/DivulgaEditais/rest/notice/";
	
	if(userId){
		url+='userRegisteredNotices/'+userId;
	} 
	
	genericList("listNotices.html", url, "notices", 
			fieldsArray, userId, function(data){
				data['tradingDate'] = getFormatedDate(data.tradingDate);
				data['publishingDate'] = getFormatedDate(data.publishingDate);
				data['closingDate'] = getFormatedDate(data.closingDate);
				data['edital'] = '<a id="open" href="Editais/' + data.fileName +'"> Abrir Edital </a>';		
			});
 }

function listCategories(){	
	genericList("listCategories.html", "/DivulgaEditais/rest/category", "category", ['description']);
 }

function listModalities(){
	genericList("listModalities.html", "/DivulgaEditais/rest/modality", "modality", ['acronyms', 'description']);
}

function listCompanyTypes(){
	genericList("listCompanyTypes.html", "/DivulgaEditais/rest/companyTypes", "company", ['acronyms', 'description']);
}

function genericList(htmlPageToLoad, urlToGetData, dataTableId, dataTableFieldsArray, parameter, eachRowCallback){
	$('#contentDiv').load(htmlPageToLoad);
	
	getEntity(urlToGetData, function(json)
	{		
        $.each(json, function(i, data) {        	
        	
        	if(eachRowCallback){
        		eachRowCallback(data);
        	}
        	
        	var row = '<tr>' +
				getTableData(data, dataTableFieldsArray) +
	  		  	'</tr>';
        	
        	$('#'+dataTableId).find('tbody:last').append(row);
        }); 
	},{parameter});		
}
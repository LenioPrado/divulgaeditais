function deleteNotice(notice){
	notice['closingDate'] = convertFormDateToSqlDate(notice['closingDate']);
	notice['publishingDate'] = convertFormDateToSqlDate(notice['publishingDate']);
	notice['tradingDate'] = convertFormDateToSqlDate(notice['tradingDate']);
	delete notice['edital'];
}

function deleteCategory(category){
	// Alterar a entidade, se necessário.
}

function deleteModality(modality){
	// Alterar a entidade, se necessário.
}

function deleteCompanyType(companyType){
	// Alterar a entidade, se necessário.
}

function deleteEntity(urlToGetData, entity, entityId, beforeDeleteEntityCallback){	

	delete entity['edit'];
	delete entity['delete'];
	
	if(beforeDeleteEntityCallback){
		beforeDeleteEntityCallback(entity);
	}
	
	$.ajax({
		   type: "post",
		   dataType: "json",
		   url: getServerUrl() + urlToGetData,
		   data: JSON.stringify( entity ),
		   processData: true,
		   contentType: 'application/json',
		   success: function(data){
			   showMessage(data['message'],'success');
			   $('#tr'+entityId).hide();
			   window.scrollTo(0, 0);
		   },
		   error: function(jqXHR, exception) {
				var msg = jqXHR.responseText;
				showMessage(msg,'error');
				window.scrollTo(0, 0);
		   }
		});
 }
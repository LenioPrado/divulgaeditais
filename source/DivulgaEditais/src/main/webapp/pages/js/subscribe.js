function listNoticesToSubscribe(){
	var urlToLoad = "subscribeNotices.html";
	var urlToGetData = "/DivulgaEditais/rest/notice/listNotSubscribed";
	var urlToDeleteSubscribe = "/DivulgaEditais/rest/notice/subscribe/";	
	
	var fieldsArray = ['user.fantasyName', ['modality.acronyms','modality.description'], 
		'companyType.description','number', 'object', 
		'publishingDate', 'tradingDate', 'closingDate','edital', 'delete'];
	
	loadPage(urlToLoad, function(){
		loadConfirmModal(urlToDeleteSubscribe, deleteNotice);
		genericList(urlToGetData, "notice", "noticeId", fieldsArray, subscribeButton, null, eachNoticeRowListing);
	});
}

function subscribeButton(data, dataTableId, entityId){
	data['delete'] = '<input type="image" entity-data="'+data[entityId]+'" data-toggle="modal" data-target="#confirmDelete" src="assets/css/images/subscribe.png"/>';
}

function listCategoriesToSubscribe(){
	
}

function subscribeCategory(){	

}
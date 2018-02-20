function loadNotice(){
	
	$('#contentDiv').load("registerNotice.html", function(){
		
		$('#submitNotice').click(function(){
			validateNotice(function(form){
				registerNotice();
			});
		});
		
		getEntity('/DivulgaEditais/rest/modality', function(json)
		{		
			fillSelect(json, 'modality', 'modalityId',['acronyms','description']);
		});	
		
		getEntity('/DivulgaEditais/rest/companyTypes', function(json)
		{		
			fillSelect(json, 'companyType', 'companyTypeId',['acronyms','description']);
		});	

		getEntity('/DivulgaEditais/rest/category', function(json)
		{		
			fillSelect(json, 'categories', 'categoryId','description');
		});			
	});	
}

function loadCategory(){
	
	$('#contentDiv').load("registerCategory.html");
	
	$('#submitCategory').click(function(){
		validateCategory(function(form){
			registerCategory();
		});
	});	
}

function loadModality(){

	$('#contentDiv').load("registerModality.html");
	
	$('#submitModality').click(function(){
		validateModality(function(form){
			registerModality();
		});
	});
}

function loadCompanyType(){

	$('#contentDiv').load("registerCompanyType.html");
	
	$('#submitCompanyType').click(function(){
		validateCompanyType(function(form){
			registerCompanyType();
		});
	});
}
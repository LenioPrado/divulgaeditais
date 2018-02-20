window.onload = function() {
	$('#sidebarDiv').load("sidebar.html", function(){
		assignEvents();
	});	
	
	$('#header').load("header.html", function(){
		// Header Load Event
	});	
	
	$('#footer').load("footer.html", function(){
		// Footer Load Event
	});	
};

function assignEvents(){	
	$('#listNoticesLink').click(function(){listNotices();});
	$('#listNoticesRegisteredByUserLink').click(function(){listNoticesRegisteredByUser();});
	$('#listCategoriesLink').click(function(){listCategories();});
	$('#listModalitiesLink').click(function(){listModalities();});
	$('#listCompanyTypesLink').click(function(){listCompanyTypes();});

	$('#registerNoticeLink').click(function(){loadNotice();});
	$('#registerCategoriesLink').click(function(){loadCategory();});	
	$('#registerModalityLink').click(function(){loadModality();});
	$('#registerCompanyTypeLink').click(function(){loadCompanyType();});	
}
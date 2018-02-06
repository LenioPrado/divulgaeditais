function show(selected){
    var branch = document.getElementById("branch");
    var cnae = document.getElementById("cnae");
    var option = document.getElementById("identification").value;
    var show = document.getElementById(option);

    if(show == branch){
        show.style.display = "block";
        cnae.style.display = "none";
    } else{
        show.style.display = "block";
        branch.style.display = "none";
    }        
}

function validation() {
//  $("form[name='registerForm']").validate({
//            rules: {
//                name: "required",
//                email: {
//                    required: true,
//                    email: true
//                },
//                password: {
//                    required: true,
//                    minlength: 5
//                },
//                identification: "required",
//                zipcode: "required",
//                street: "required",
//                number: "required",
//                city: "required",
//                state: "required",
//                primaryPhoneNumber: "required",
//                mainPerson: "required",
//            },
//
//            messages: {
//                name: "Digite um nome válido",
//                password: {
//                    required: "Digite uma senha válida",
//                    minlength: "Sua senha deve ter no mínimo 5 caracteres"
//                },
//                email:  "Digite um email válido",
//                identification: "Selecione um tipo válido",
//                zipcode: "Digite um CEP válido",
//                street: "Digite uma rua válida",
//                number: "Digite um numero válido",
//                city: "Digite uma cidade válida",
//                state: "Digite um estado válido",
//                primaryPhoneNumber: "Digite um telefone válido",
//                mainPerson: "Digite um nome válido",
//            },
//            errorClass: "registerError",
//            validClass: "registerSuccess",
//
//       submitHandler: function(form) {
//        saveUser();
//    }
//  });
  saveUser();
}

function saveUser()
      {
        var jsonArray = [];
        var i=0;
        var splittedFormData = $("form[name='registerForm']").serialize().split('&');

        item = {};
        increment = {};
        $.each(splittedFormData, function (key, value) {
            var splittedValue = value.split('=');               
            item[splittedValue[0]] = splittedValue[1];
        });
        
        jsonArray.push(item);
        
    	var modality = {
    			name: "PRExy",
    			email: "Tomada de Preço"
    	}
    	
    	console.log(jsonArray);
            
        var baseUrl = getServerUrl();
    	
    	$.ajax({
    	   type: "post",
    	   dataType: "json",
    	   url: baseUrl + "/DivulgaEditais/rest/user/create",
    	   data: jsonArray, //JSON.stringify( jsonArray )
    	   processData: true,
    	   contentType: 'application/json',
    	   success: function(data){
    		   alert('Salvo com sucesso!');
    	   },
    	   failure: function(errMsg) {
    	       alert('Erro:' + errMsg);
    	   }
    	});

      }
    

    
function checkUser(){
        var jsonArray = [];
        var i=0;
         var splittedFormData = $("form[name='login']").serialize().split('&');

                item = {};
                increment = {};
            $.each(splittedFormData, function (key, value) {
                var splittedValue = value.split('=');               
                item[splittedValue[0]] = splittedValue[1];
                });
            jsonArray.push(item);
          var myEmail = decodeURIComponent(item['userEmail']);  
          var myPassword = item['userPassword'];
          console.log(myEmail + " " + myPassword);
          
            $.getJSON("users.json", function(json) {     
                $.each(json, function(i, value) {
                    if((value.email)===myEmail){
                        if((value.password)===myPassword){
                            console.log("Achou um usuario igual " +value.email+ " "+value.password+ " " +value.identification+ " " +value.name);
                            
                            $.ajax({
                                url : 'userSession.php',
                                type : 'POST',
                                data: value
                            });
                            
                            if(value.identification == "branch"){
                                window.location.replace("mainUser.php");
                            }
                            if(value.identification == "cnae"){
                                window.location.replace("companyUser.php");
                            }
                        }
                        else{
                            alert("Senha incorreta");
                        }
                    }

                }); 
                console.log("não achou nenhum igual");
            });

        return true;
}
    
function validationLogin(){
    console.log("entrou no validationLogin");
$("form[name='login']").validate({
    rules: {
        userEmail: {
            required: true,
            email: true
            },
        userPassword: {
            required: true,
            minlength: 5
        },
    },
    messages: {
        userPassword: {
            required: "Digite uma senha válida",
            minlength: "Sua senha deve ter no mínimo 5 caracteres"
        },
        userEmail:  "Digite um email válido",
    },
    submitHandler: function(form) {
        checkUser();
    },
        errorClass: "registerError",
        validClass: "registerSuccess"

  });
}
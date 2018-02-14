function show(selected){
    var branch = document.getElementById("branch");
    var cnae = document.getElementById("cnae");
    var option = document.getElementById("type").value;
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
	var option = $("#type").val();
	if(option == "branch"){
		$("form[name='registerForm']").validate({
            rules: {
            	branch: "required"
            },
            messages: {
            	branch: "Digite um setor válido"
            }
		});
	}else{
		$("form[name='registerForm']").validate({
            rules: {
            	cnae: "required",
            	companyType: "required"
            },
            messages: {
            	cnae: "Digite um CNAE válido",
            	companyType: "Selecione uma categoria válida"
            }
		});
	}
	
  $("form[name='registerForm']").validate({
            rules: {
            	socialName: "required",
            	fantasyName: "required",
                email: {
                    required: true,
                    email: true
                },
                password: {
                    required: true,
                    minlength: 5
                },
                cnpj: "required",
                type: "required",
                zipCode: "required",
                address: "required",
                number: "required",
                city: "required",
                state: "required",
                phonePrimary: "required",
                responsibleName: "required",
                responsibleCpf: "required"
            },

            messages: {
            	socialName: "Digite uma razão social válida",
            	fantasyName: "Digite um nome fantasia válida",
                password: {
                    required: "Digite uma senha válida",
                    minlength: "Sua senha deve ter no mínimo 5 caracteres"
                },
                email:  "Digite um email válido",
                cnpj: "Digite um CPF válido",
                type: "Selecione um tipo válido",
                zipCode: "Digite um CEP válido",
                address: "Digite uma rua válida",
                number: "Digite um numero válido",
                city: "Digite uma cidade válida",
                state: "Digite um estado válido",
                phonePrimary: "Digite um telefone válido",
                responsibleName: "Digite um nome válido",
                responsibleCpf: "Digite um CPF válido"
            },
            errorClass: "registerError",
            validClass: "registerSuccess",

       submitHandler: function(form) {
        saveUser();
    }
  });
};

function saveUser()
  {
	var user = getFormData('registerForm');
    var baseUrl = getServerUrl();

	$.ajax({
	   type: "post",
	   dataType: "json",
	   url: baseUrl + "/DivulgaEditais/rest/user/create",
	   data: JSON.stringify( user ),
	   processData: true,
	   contentType: 'application/json',
	   success: function(data){
		   alert('Salvo com sucesso!');
	   },
	   failure: function(errMsg) {
	       alert('Erro:' + errMsg);
	   },
	   error: function(jqXHR, status, error) {
		   if(jqXHR.responseText !== ''){
		        alert(status+": "+jqXHR.responseText);
		    }else{
		        alert(status+": "+error);
		    }  
	   }
	});    	
};

    
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
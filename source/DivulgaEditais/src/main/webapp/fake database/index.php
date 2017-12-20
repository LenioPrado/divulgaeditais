<!DOCTYPE html>
<html>
    
<head>
	<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="assets/css/main.css" />
    <meta charset="utf-8">
    
      <title>Prefeitura Municipal de Poços de Caldas</title>
  <script src="js/jquery-3.2.1.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="//malsup.github.io/jquery.form.js"></script>
  <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"> </script>
    
</head>
<body>
    <script>
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
        
    </script>
    <section id="header">
        <header>
            <h1>Sistema de Cadastro de Editais</h1>
            <p>Prefeitura Municipal de Poços de Caldas</p>
        </header>
        <footer>
            <a href="#login" class="button style2 scrolly-middle">Entrar</a>
            <a href="#register" class="button style2 scrolly">Cadastrar</a>
        </footer>
    </section>
    <section id="login">
        <article class="container box style3">
             <header>
                <h2>Entrar</h2>
                <p>Confira todos os editais da prefeitura.</p>
            </header>
             <form name="login" method="post" onSubmit="return validationLogin();" action="javascript:console.log('success!');">
                <label name="labelUser"> Email</label>
                <input type="text" name="userEmail"/><br>
                <label name="labelUserPassword"> Senha</label>
                <input type="password" name="userPassword"/><br>
                <input type="submit" value="Entrar"/>
            </form>
        </article>
    </section>

<section id="register">
    <article class="container box style3">
        <header>
            <h2>Cadastrar</h2>
            <p>Cadastre-se e fiquei por dentro de todos os editais da prefeitura.</p>
        </header> 
    <form name= "registerForm" id="registerForm" method="post" onSubmit="return validation();" action="javascript:console.log('success!');">
        <label type="text" name="labelName"> Nome</label>
        <input type="text" name="name" id="name" /> <br>
        <label type="text" name="labelEmail"> Email </label>
        <input type="text" name="email" id="email"/> <br>
        <label type="text" name="labelPassword"> Senha</label>
        <input type="password" name="password" id="password" /> <br>
        <label type="text" name="labelIdentification"> Identificação</label>
        <select id="identification" name="identification" onchange="show(this);">
                <option value="none" selected="selected">(Selecione uma opção)</option>
                <option value="branch">Usuário da Prefeitura</option>
                <option value="cnae">Empresa concorrente</option>
        </select> <br>
        <div id="branch" style="display:none">
        <label type="text" name="labelBranch"> Setor de atuação</label>
        <input type="text" name="branch" id="branch"/> <br>
        </div>
        <div id="cnae" style="display:none">
        <label type="text" name="labelBranch"> CNAE </label>
        <input type="text" name="cnaeNumber" id="cnaeNumber"/> <br>
        <label type="text" name="labelSize"> Tipo de Empresa</label>
        <select id="companySize" name="companySize">
                <option value="" selected="selected">(Selecione uma opção)</option>
                <option value="MEI">Microempreendedor Individual</option>
                <option value="ME">Microempresa</option>
                <option value="EPP">Empresa de Pequeno Porte</option>
                <option value="SA">Sociedade Anônima (SA)</option>
                <option value="LTDA">Sociedade Limitada (LTDA)</option>
        </select> <br>
        </div>
        <label type="text" name="labelZipCode"> CEP</label>
        <input type="text" name="zipcode" id="zipcode"/> <br>
        <label type="text" name="labelStreet"> Endereço</label>
        <input type="text" name="street" id="Street"/> <br>
        <label type="text" name="labelNumber"> Numero</label>
        <input type="text" name="number" id="Number"/> <br>
        <label type="text" name="labelComplement"> Complemento</label>
        <input type="text" name="complement" id="Complement"/> <br>
        <label type="text" name="labelNeighbourhood"> Bairro</label>
        <input type="text" name="neighbourhood" id="Neighbourhood"/> <br>
        <label type="text" name="labelCity"> Cidade</label>
        <input type="text" name="city" id="city"/> <br>
        <label type="text" name="labelState"> Estado</label>
        <input type="text" name="state" id="state"/> <br>
        <label type="text" name="labelPrimaryPhoneNumber"> Telefone Principal</label>
        <input type="text" name="primaryPhoneNumber" id="primaryPhoneNumber"/> <br>
        <label type="text" name="labelSecondPhoneNumber"> Celular</label>
        <input type="text" name="secondPhoneNumber" id="secondPhoneNumber"/> <br>
        <label type="text" name="labelMainPerson"> Responsável</label>
        <input type="text" name="mainPerson" id="mainPerson"/> <br>
        <input type="submit" value="Cadastrar">
    </form>
    </article>
</section>

<script>
    function validation() {
  $("form[name='registerForm']").validate({
            rules: {
                name: "required",
                email: {
                    required: true,
                    email: true
                },
                password: {
                    required: true,
                    minlength: 5
                },
                identification: "required",
                zipcode: "required",
                street: "required",
                number: "required",
                city: "required",
                state: "required",
                primaryPhoneNumber: "required",
                mainPerson: "required",
            },

            messages: {
                name: "Digite um nome válido",
                password: {
                    required: "Digite uma senha válida",
                    minlength: "Sua senha deve ter no mínimo 5 caracteres"
                },
                email:  "Digite um email válido",
                identification: "Selecione um tipo válido",
                zipcode: "Digite um CEP válido",
                street: "Digite uma rua válida",
                number: "Digite um numero válido",
                city: "Digite uma cidade válida",
                state: "Digite um estado válido",
                primaryPhoneNumber: "Digite um telefone válido",
                mainPerson: "Digite um nome válido",
            },
            errorClass: "registerError",
            validClass: "registerSuccess",

       submitHandler: function(form) {
        saveUser();
    }
  });
}
function saveUser()
      {
        console.log("chegou no post");
         $('#registerForm').ajaxSubmit({
            url  : 'writeUser.php',
            type : 'POST'
         });
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
          
           $.ajax({
                url : 'userSession.php',
                type : 'POST',
                data: jsonArray
            });
                        
            var myType = item['identification'];
          
            if(myType == "branch"){
                window.location.replace("mainUser.php");
            }
            if(myType == "cnae"){
                window.location.replace("companyUser.php");
            }
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

 </script> 
</body>
</html>


			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/jquery.poptrox.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Prefeitura Municipal de Poços de Caldas</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="master/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="master/bower_components/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="master/bower_components/Ionicons/css/ionicons.min.css">
  <link rel="stylesheet" href="master/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="master/dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

  <script src="js/jquery-3.2.1.min.js"></script>
  <script src="modality.json"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="//malsup.github.io/jquery.form.js"></script>
  <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"> </script>

</head>
<body class="hold-transition skin-blue sidebar-mini">
  <script>
    $("#tableNotice").hide();
    $("#register").hide();

function sendNotice(number){
          console.log(number);
          
          $.ajax({
                    url  : 'teste.php',
                    type : 'POST',
                    data: number
         });
      }
      
function fillAllNotice(){
  console.log("Entrou na funcao");

        $.getJSON("notice.json", function(json) {
            $("#notices tbody tr").remove();       
            $.each(json, function(i, value) {
          $('#notices > tbody:last-child').append('<tr><td>' + value.modality+ '</td><td>' + value.number + '</td><td>' + value.object + '</td><td>' + value.trading_date + '</td><td><a id="open" href="Editais/'+value.number+'.pdf"> Abrir Edital </a></td></tr>');
           //onClcick="sendNotice('+value.number+')"
           console.log(value);
        }); 
    }); 

  }


$.getJSON('modality.json', function(json) {
  $select = $('#modality');
  $.each(json, function(i, value) {
    //<option value="townhall">Usuário da Prefeitura</option>
           $select.append('<option id="' + value.acronyms+ '">' + value.description + '</option>');
        });
    console.log(json); // this will show the info it in firebug console
});

function loadRegister(){
    $("#tableNotice").hide();
    $("#register").show();
  }

function loadTable(){
    $("#register").hide();
    $("#tableNotice").show();
    fillAllNotice();

  }
      
</script>
<div class="wrapper">

  <header class="main-header">
    <a class="logo">
      <span class="logo-mini"><b>SE</b></span>
      <span class="logo-lg"><b>Sistema de Editais</b></span>
    </a>
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">

          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="master/dist/img/usuario-160x160.png" class="user-image" alt="User Image">
              <a class="hidden-xs" href="index.php">Sign out</a>
            </a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
      <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="master/dist/img/usuario-160x160.png" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Bem-vindo</p>
        </div>
      </div>
           <ul class="sidebar-menu" data-widget="tree">
        <li class="treeview">
          <a onclick="javascript: loadRegister();">
            <i class="fa fa-dashboard"></i> <span>Cadastrar Edital</span>
          </a>
          <a onclick="javascript: loadTable();">
            <i class="fa fa-dashboard"></i> <span>Todos os Editais</span>
          </a>
        </li>
      </ul>
    </section>
  </aside> 
      <div class="content-wrapper">
<section id="register" style="display:none">
    <section class="content-header">
      <h1>
        Cadastrar Edital
      </h1>
    </section>
    <section class="content">
      <div class="row">
        <div class="col-md-12">
          <div class="box box-primary">
            <form name= "registerNotice" id="registerNotice" method="post" onSubmit="return validation();" action="javascript:console.log('success!');" enctype="multipart/form-data">
              <div class="box-body">
                <div class="form-group">
                  <label type="text" name="labelModality"> Modalidade</label>
                      <select id="modality" name="modality">
                      </select>
                </div>
                <div class="form-group">
                  <label type="text" name="labelNumber"> Numero </label>
                  <input type="number" name="number" id="number" /> <br>
                </div>
                <div class="form-group">
                  <label type="text" name="labelObject"> Objeto </label>
                        <input type="text" name="object" id="object"/> <br>
                </div>
                <div class="form-group">
                  <label type="text" name="labelDate"> Data do Pregão</label>
                        <input type="date" name="trading_date" id="trading_date" /> <br>
                </div>
                <div class="form-group">
                  <label for="exampleInputFile">File input</label>
                  <input type="file" name="file" id="file" accept=".pdf"/>
                </div>
              </div>
              <div class="box-footer">
                    <input type="submit" value="Cadastrar" class="btn btn-primary">
                  </div>
            </form>
            </div>
        </div>
      </div>
    </section>
</section>

<section id="tableNotice" style="display:none">
    <section class="content-header">
      <h1>
        Editais Cadastrados
      </h1>
    </section>
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
            </div>
            <div class="box-body">
              <table name="notices" id="notices" class="table table-bordered table-hover">
                <thead>
                  <tr>
                      <th>Modalidade</th><th>Numero</th><th>Objeto</th><th>Data do Pregao</th> <th>Selecionar</th>
                  </tr>
                </thead>
                <tbody>
                  
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </section>
</section>
    </div>
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 1.0
    </div>
    <strong>Prefeitura Municipal de Poços de Caldas</strong>
  </footer>
</div>
<script>
  function validation() {
  $("form[name='registerNotice']").validate({
            rules: {
               modality: "required",
                number: "required",
                object: "required",
                trading_date: "required",
                //file: "required",
            },

            messages: {
                modality: "Escolha uma modalidade válida",
                number: "Digite um número válido",
                object: "Digite uma descrição válida",
                trading_date: "Selecione uma data válida",
                //file: "Insira um arquivo valido",
            },
            errorClass: "registerError",
            validClass: "registerSuccess",

    submitHandler: function(form) {
        saveFile();
        alert("Edital cadastrado com sucesso!");
        location.reload(this);
    }
  });
      function saveFile()
      {
         $('#registerNotice').ajaxSubmit({
            url  : 'writeNotice.php',
            type : 'POST'
         });
      }
      
    /*function submit(){
        saveFile();

        var jsonArray = [];
        var i=0;
         var splittedFormData = $("form[name='registerNotice']").serialize().split('&');

                item = {};
                increment = {};
            $.each(splittedFormData, function (key, value) {
                var splittedValue = value.split('=');               
                item[splittedValue[0]] = splittedValue[1];
            });
            jsonArray.push(item);

        //console.log(jsonArray);
        
        return true;
    } */
}
</script>
    <!-- <script src="js/require.js"></script>
    <script> -->

<script src="master/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="master/bower_components/fastclick/lib/fastclick.js"></script>
<script src="master/dist/js/adminlte.min.js"></script>
<script src="master/dist/js/demo.js"></script>
</body>
</html>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Prefeitura Municipal de Poços de Caldas</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="master/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="master/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="master/bower_components/Ionicons/css/ionicons.min.css">
  <!-- DataTables -->
  <link rel="stylesheet" href="master/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="master/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="master/dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
  <script src="js/jquery-3.2.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"> </script>
  <script src="notice.json"></script>
  <script>
      function sendNotice(number){
          console.log(number);
          
          $.ajax({
                    url  : 'show.php',
                    type : 'POST',
                    data: number
         });
      }
  function fillAllNotice(){
  console.log("Entrou na funcao");
$.getJSON('notice.json', function(json) {
  console.log("pegou o json");
  $("#notices tbody tr").remove();
  $.each(json, function(i, value) {
          $('#notices > tbody:last-child').append('<tr><td>' + value.modality+ '</td><td>' + value.number + '</td><td>' + value.object + '</td><td>' + value.trading_date + '</td><td><a id="open" href="Editais/'+value.number+'.pdf"> Abrir Edital </a></td></tr>');
           // onClcick="sendNotice('+value.number+')"
           console.log(value);
        });
    console.log(json); // this will show the info it in firebug console
});
}
  function fillRegisteredNotice(){
  console.log("Entrou na funcao");
$.getJSON('registeredNotice.json', function(json) {
  console.log("pegou o json dos editais cadastrados");
  $("#notices tbody tr").remove();
  $.each(json, function(i, value) {
          $('#notices > tbody:last-child').append('<tr><td>' + value.modality+ '</td><td>' + value.number + '</td><td>' + value.object + '</td><td>' + value.trading_date + '</td><td>' + value.url + '</td><td><button id="open" onclick="sendNotice('+value.number+')"> Abrir Edital </button></td></tr>');
           // onClcick="sendNotice('+value.number+')"
           console.log(value);
        });
    console.log(json); // this will show the info it in firebug console
});
}
</script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
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
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="master/dist/img/usuario-160x160.png" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Bem-vindo></p>
        </div>
      </div>
            <ul class="sidebar-menu" data-widget="tree">
        <li class="treeview">
          <a onclick="javascript: fillAllNotice();">
            <i class="fa fa-dashboard"></i> <span>Todos os Editais</span>
          </a>
          <a onclick="javascript: fillRegisteredNotice();">
            <i class="fa fa-dashboard"></i> <span>Editais Inscritos</span>
          </a>
        </li>
      </ul>
    </section>
  </aside> 

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Editais Disponíveis
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
            </div>
            <!-- /.box-header -->
            <div class="box-body">
<table name="notices" id="notices" class="table table-bordered table-hover">
  <thead>
    <tr>
        <th>Modalidade</th><th>Numero</th><th>Objeto</th><th>Data do Pregao</th><th>Selecionar</th>
    </tr>
  </thead>
  <tbody></tbody>
</table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 1.0
    </div>
    <strong>Prefeitura Municipal de Poços de Caldas</strong>
  </footer>

    </div>
<!-- jQuery 3 -->
<script src="master/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="master/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="master/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="master/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="master/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="master/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="master/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="master/dist/js/demo.js"></script>
<!-- page script -->
</body>
</html>

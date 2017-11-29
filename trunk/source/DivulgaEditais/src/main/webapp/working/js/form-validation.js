//$(function validation() {
//  $("form[name='registerForm']").validate({
//    rules: {
//      name: "required",
//      email: {
//        required: true,
//        email: true
//      },
//      password: {
//        required: true,
//        minlength: 5
//      },
//    identification: "required",
//    zipcode: "required",
//    street: "required",
//    number: "required",
//    city: "required",
//    state: "required",
//    primaryPhoneNumber: "required",
//    mainPerson: "required",
//    },
//
//    messages: {
//      name: "Digite um nome válido",
//      password: {
//        required: "Digite uma senha válida",
//        minlength: "Sua senha deve ter no mínimo 5 caracteres"
//      },
//      email:  "Digite um email válido",
//      identification: "Selecione um tipo válido",
//      zipcode: "Digite um CEP válido",
//      street: "Digite uma rua válida",
//      number: "Digite um numero válido",
//      city: "Digite uma cidade válida",
//      state: "Digite um estado válido",
//      primaryPhoneNumber: "Digite um telefone válido",
//      mainPerson: "Digite um nome válido",
//
//    },
//    submitHandler: function(form) {
//      form.submit();
//    }
//  })
//  
//  var jsonArray = [];  
//  var splittedFormData = $("registerForm").serialize().split('&');
//  
//  $("form[name='registerForm']").submit({
//            $.each(splittedFormData, function (key, value) {
//
//                item = {};
//                var splittedValue = value.split('=');               
//                item["name"] = splittedValue[0];
//                item["value"] = splittedValue[1];
//                jsonArray.push(item);
//            });
//
//        console.log(jsonArray);
//  })
//
//    
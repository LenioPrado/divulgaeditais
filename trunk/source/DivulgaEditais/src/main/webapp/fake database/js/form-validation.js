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

    submitHandler: function(form) {
        submit();
    }
  });
    function submit(){
        //$("form[name='registerForm']").submit(function(){

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

        console.log(jsonArray);
        return true;
        //});
    }
}

</script>

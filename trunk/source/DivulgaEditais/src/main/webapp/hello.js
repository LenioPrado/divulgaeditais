/**
 * 
 */
angular.module('demo', [])
.controller('Hello', function($scope, $http) {
    $http.get('http://localhost:8080/DivulgaEditais/rest/modality').
        then(function(response) {        	
            $scope.greeting = response.data;
        });
})
.controller('validatePassword', function($scope, $http) {
	$scope.enviar = function() {
        $email = $('txtEmail').val();
        $cpf = $('txtCPF').val();
        alert($email + ' - ' + $cpf);
      };	
});
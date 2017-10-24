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
        $email = $('#txtEmail').val();
        $cpf = $('#txtCPF').val();
        var msgdata = {"modalityId":1,"acronyms":"abc","description":"This is a test","notices":[]};
        var res = $http.post('http://localhost:8080/DivulgaEditais/rest/modality/login',msgdata);
        res.success(function(data, status, headers, config) {
            console.log(data);
        }).error(function() {
            alert("zebra");
        });
        return res;
      };	
});
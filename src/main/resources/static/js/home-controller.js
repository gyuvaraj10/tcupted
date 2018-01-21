var app = angular.module('tcupted');
app.controller('home', ['$scope','$state', function($scope, $state){

    $scope.gotoCompany = function() {
         $state.go('company');
    }

}]);
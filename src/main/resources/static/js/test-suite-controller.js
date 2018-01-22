var app = angular.module('tcupted');

app.controller('testsuite', ['$scope','$http','$state','$stateParams', function($scope, $http, $state, $stateParams) {

    $http.get('/test/getAll').then(function(response){
        $scope.tests = response.data
    }).catch(function(error){
        console.log(error);
    })

    $scope.hideCreateTestButton = true;

    $scope.showCreateTestButton = function() {
      $scope.hideCreateTestButton = false;
    }

    $scope.createTest = function() {
        var name = $scope.testCaseName;
        var description = $scope.testCaseDescription;
        $http.post("/test/create", {testName: name, testDescription: description})
        .then(function(data){
            $state.transitionTo($state.current, $stateParams, {
                            reload: true,
                            inherit: false,
                            notify: true
                        });
            console.log('Successfully Created a Test');
            console.log(data);
         }).catch(function(error) {
            console.log(error);
         });
    }

}]);
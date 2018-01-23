var app = angular.module('tcupted');
app.controller('project', ['$scope','$http', '$state','$stateParams', function($scope, $http,$state,$stateParams) {

    $scope.isTestSuiteCreated = true;
    var project = $stateParams.project;
    $scope.project = project;
    console.log('project name is');
    console.log($stateParams.project);

    $http.get('/suite/'+project).then(function(data){
      $scope.suites = []
      data.data.forEach(function(item){
        $scope.suites.push({
           name: item.testSuiteName,
           description: item.testSuiteDescription
        });
      });
    }).catch(function(error) {
        console.log(error);
    });

    $scope.createTestSuite = function() {
        let name = $scope.testSuiteName;
        let description = $scope.testSuiteDescription;

        $http({
                method:'POST',
                url: '/suite/create/'+project+"/"+ name,
                params: {description: description}
            }).then(function(data) {
            $state.transitionTo($state.current, $stateParams, {
                reload: true,
                inherit: false,
                notify: true
            });
            console.log(data);
        }).catch(function(error) {
            console.log(error);
        });
    }
    $scope.showCreateTestSuite = function() {
        $scope.isTestSuiteCreated = false;
    };

}]);
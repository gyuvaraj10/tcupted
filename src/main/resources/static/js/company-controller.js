var app = angular.module('tcupted');
app.controller('company', ['$scope','$http', '$state','$stateParams', function($scope, $http,$state,$stateParams) {

    $scope.iscreateCompany = true;
    $scope.companies = $stateParams.companies;

    $scope.createCompany = function() {
        let companyName = $scope.companyName;
        let companyEmail = $scope.companyEmail;
        let projectName = $scope.projectName;
        let projectDesc = $scope.projectDesc;

        let companyObj =  {
           name: companyName,
           email: companyEmail,
           projectList: [{
               projectName: projectName,
               description: projectDesc,
               }
           ]
        }
        $http.post('/company/create', companyObj).then(function(data) {
            $state.transitionTo($state.current, $stateParams, {
                reload: true,
                inherit: false,
                notify: true
            });

        }).catch(function(error) {
            console.log(error);
        });
    }

    $scope.showCreateCompanyCompany = function() {
        $scope.iscreateCompany = false;
    };

}]);
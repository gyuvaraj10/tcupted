var app = angular.module('tcupted');
app.controller('company', ['$scope','$http', '$state','$stateParams', function($scope, $http,$state,$stateParams) {

    $scope.iscreateCompany = true;
    console.log('company controller');

    if($stateParams.companies == null){
        $http.get('/company/getAll').then(function(data){
                var companies = []
                console.log('entered');
                data.data.forEach(function(item){
                  companies.push({
                     name: item.name,
                     email: item.email,
                     projects: item.projectList
                  });
                });
           $scope.companies = companies;
        }).catch(function(error){
            console.log(error);
        })
    } else {
        $scope.companies = $stateParams.companies;
    }


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
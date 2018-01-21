var app = angular.module('tcupted');
app.controller('home', ['$scope','$state','$http', function($scope, $state,$http){

    $scope.gotoCompany = function() {
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
            $state.go('company', {companies: companies});
          }).catch(function(error) {
              console.log(error);
          });
    }

}]);
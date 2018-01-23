var app = angular.module('tcupted');
app.directive('companyDetail', function($http){

    function link(scope) {
        scope.projects = []
        $http.get('/company/getAll').then(function(response){
            var companies = []
            console.log('entered');
            response.data.forEach(function(item){
                  companies.push({
                     name: item.name,
                     email: item.email,
                     projects: item.projectList
                  });
                });
        if(companies!=null && companies.length > 0) {
            companies.forEach(function(company){
                var proj = {
                    name: company.name,
                    email: company.email,
                }
                if(angular.isDefined(company.projects)) {
                    company.projects.forEach(function(projectItem){
                        proj.projectName = projectItem.projectName;
                        scope.projects.push(proj);
                    })
                }
            });
        }
    }).catch(function(error) {
      console.log(error);
    })
  }

    return {
        restrict: 'AE',
        link:link,
        scope: {
            companies: '=companies'
        },
        templateUrl:'/views/company-detail-directive.html' 
    }
});
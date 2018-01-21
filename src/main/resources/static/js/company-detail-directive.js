var app = angular.module('tcupted');
app.directive('companyDetail', function(){

    function link(scope) {
        scope.projects = []
        let companies = scope.companies;
        companies.forEach(function(company){
            var proj = {
                name: company.name,
                email: company.email,
            }
            company.projects.forEach(function(projectItem){
                proj.projectName = projectItem.projectName;
                scope.projects.push(proj);
            })
        });
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
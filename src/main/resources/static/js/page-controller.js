var app = angular.module('tcupted');
app.controller('page', ['$scope', '$state', '$stateParams', '$http', function($scope, $state, $stateParams,$http) {

    var pageName = $stateParams.page;
    var projectName = $stateParams.project;
    $scope.project = projectName;

    $http.get('/'+projectName+'/'+pageName+'/all').then(function(response){
        var pages = response.data;
        var elements = [];
        if(pages!=null && angular.isDefined(pages) && pages.length >0){
            pages.forEach(function(page) {
                elements.push({
                    identifier: page.elementIdentifier,
                    identifierValue: page.elementIdentifierValue,
                    elementName: page.elementName
                    });
            })
        }
        $scope.elements = elements;

    }).catch(function(error){
        console.log(error);
        $scope.elements = [];
    })

      //call before saving the form
      $scope.checkName = function(data, elementName) {
//        if (id === 2 && data !== 'awesome') {
//          return "Username 2 should be `awesome`";
//        }
      };

      $scope.saveElement = function(data, elementName) {

        angular.extend(data, {elementName: elementName});
        var page = {
                pageName: pageName,
                projectName: projectName,
                elementIdentifier: data.elementIdentifier,
                elementIdentifierValue: data.elementIdentifierValue,
                elementName: data.elementName
        }
        return $http.post('/create/'+projectName+'/'+pageName, page);
      };

      // remove user
      $scope.removeElement = function(index) {
        $scope.elements.splice(index, 1);
      };

      // add user
      $scope.addPageElement = function() {
        $scope.inserted = {
          id: $scope.elements.length+1,
          elementName: '',
          elementIdentifier: null,
          elementIdentifierValue: null
        };
        $scope.elements.push($scope.inserted);
      };

}]);
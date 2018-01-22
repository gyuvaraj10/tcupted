var app = angular.module('tcupted');
app.controller('page', ['$scope', '$state', '$stateParams', function($scope, $state, $stateParams) {

    var columnDefs1 = [
        { name: 'elementName' },
        { name: 'identifierType' },
        { name: 'identifier' }
      ];

    $scope.gridOpts = {
       columnDefs: columnDefs1
    }

    $scope.addPageElement = function() {

    }


}]);
var app = angular.module('tcupted', ['ui.router','ngRoute']);
app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
 'use strict';
  $urlRouterProvider.otherwise('/home');

  $stateProvider.state('home', {
         url: '/home',
         views: {
           'content@': {
                    controller: 'home',
                    controllerAs: 'homeCtl',
                    templateUrl: '/views/home.html'
           }
         }
  }).
  state('company', {
        url: '/company',
        views: {
         'content@': {
                 controller: 'company',
                 controllerAs: 'companyCtl',
                 templateUrl: '/views/company.html'
          }
        },
        params: {
           companies: null
        }
  }).
  state('company.project', {
       url: '/:project',
       views: {
         'content@': {
              controller: 'project',
              controllerAs: 'projectCtl',
              templateUrl: '/views/project.html'
         }
       },
       params: {
          project: null
       }
  })
}]);
var app = angular.module('tcupted', ['ui.router','ngRoute','ui.grid']);
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
  }).
  state('company.project.suite', {
        url: '/suite',
        views: {
            'content@': {
                controller: 'testsuite',
                controllerAs: 'testsuiteCtl',
                templateUrl: '/views/suite.html'
            }
        },
        params: {
            project: null,
            suiteName: null
        }
  }).
   state('company.project.suite.test', {
          url: '/:testName',
          views: {
              'content@': {
                  controller: 'testscript',
                  controllerAs: 'testscriptCtl',
                  templateUrl: '/views/test.html'
              }
          },
          params: {
              testName: null
          }
    }).
    state('company.project.pages', {
            url: '/:page',
            views: {
                'content@': {
                    controller: 'page',
                    controllerAs: 'pageCtl',
                    templateUrl: '/views/page.html'
                }
            },
            params: {
                page: null
            }
    });
}]);
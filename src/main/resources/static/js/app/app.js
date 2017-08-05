var app = angular.module('myApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/App',
    UNIVERSITY_SERVICE_API : 'http://localhost:8080/App/api/university/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'UniversityController',
                controllerAs:'ctrl',
                resolve: {
                    universities: function ($q, UniversityService) {
                        console.log('Load all universities');
                        var deferred = $q.defer();
                        UniversityService.loadAllUniversities().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);


'use strict';

angular.module('myApp').factory('UniversityService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllUniversities: loadAllUniversities,
                getAllUniversities: getAllUniversities,
                getUniversity: getUniversity,
                createUniversity: createUniversity,
                updateUniversity: updateUniversity,
                removeUniversity: removeUniversity
            };

            return factory;

            function loadAllUniversities() {
                console.log('Fetching all universities');
                var deferred = $q.defer();
                $http.get(urls.UNIVERSITY_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all universities');
                            $localStorage.universities = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading universities');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllUniversities(){
                return $localStorage.universities;
            }

            function getUniversity(id) {
                console.log('Fetching University with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.UNIVERSITY_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully University with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading university with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createUniversity(university) {
                console.log('Creating University');
                var deferred = $q.defer();
                $http.post(urls.UNIVERSITY_SERVICE_API, university)
                    .then(
                        function (response) {
                            loadAllUniversities();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating University : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateUniversity(university, id) {
                console.log('Updating University with id '+id);
                var deferred = $q.defer();
                $http.put(urls.UNIVERSITY_SERVICE_API + id, university)
                    .then(
                        function (response) {
                            loadAllUniversities();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating University with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeUniversity(id) {
                console.log('Removing University with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.UNIVERSITY_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllUniversities();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing University with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);
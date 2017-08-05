'use strict';

angular.module('myApp').controller('UniversityController',
    ['UniversityService', '$scope',  function( UniversityService, $scope) {

        var self = this;
        self.university = {};
        self.universities=[];

        self.submit = submit;
        self.getAllUniversities = getAllUniversities;
        self.createUniversity = createUniversity;
        self.updateUniversity = updateUniversity;
        self.removeUniversity = removeUniversity;
        self.editUniversity = editUniversity;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.university.id === undefined || self.university.id === null) {
                console.log('Saving New University', self.university);
                createUniversity(self.university);
            } else {
                updateUniversity(self.university, self.university.id);
                console.log('University updated with id ', self.university.id);
            }
        }

        function createUniversity(university) {
            console.log('About to create university');
            UniversityService.createUniversity(university)
                .then(
                    function (response) {
                        console.log('University created successfully');
                        self.successMessage = 'University created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.university={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating University');
                        self.errorMessage = 'Error while creating University: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateUniversity(university, id){
            console.log('About to update university');
            UniversityService.updateUniversity(university, id)
                .then(
                    function (response){
                        console.log('University updated successfully');
                        self.successMessage='University updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating University');
                        self.errorMessage='Error while updating University '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeUniversity(id){
            console.log('About to remove University with id '+id);
            UniversityService.removeUniversity(id)
                .then(
                    function(){
                        console.log('University '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing university '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllUniversities(){
            return UniversityService.getAllUniversities();
        }

        function editUniversity(id) {
            self.successMessage='';
            self.errorMessage='';
            UniversityService.getUniversity(id).then(
                function (university) {
                    self.university = university;
                },
                function (errResponse) {
                    console.error('Error while removing university ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.university={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);
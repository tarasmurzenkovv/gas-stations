gasStation.controller('RegistrationController', ['$rootScope', '$scope', '$http', '$window','customerInformation','Customer',
    function($rootScope, $scope, $http, $window, customerInformation, Customer) {
        $scope.customerTypes = null;
        $scope.genderGroups = null;
        $scope.selectedCustomerType = null;

        angular.element(document).ready(function() {
            Customer.getCustomerTypes($http)
                .success(function(data) {
                    $scope.customerTypes = data;
                })
                .error(function(data) {
                    displayErrorMessages(data)
                });

            Customer.getGenderGroups($http)
                .success(function(data) {
                    $scope.genderGroups = data;
                })
                .error(function(data) {
                    displayErrorMessages(data)
                });
        });

        $scope.ProcessRegistration = function() {
            var newCustomerInformation = {
                firstName: '',
                lastName: '',
                dateOfBirth: '',
                login: '',
                password: '',
                email: '',
                gender: '',
                group: ''
            };
            newCustomerInformation.firstName = $scope.firstName;
            newCustomerInformation.lastName = $scope.lastName;
            newCustomerInformation.dateOfBirth = $scope.dateOfBirth;
            newCustomerInformation.login = $scope.login;
            newCustomerInformation.password = $scope.password;
            newCustomerInformation.email = $scope.email;
            newCustomerInformation.gender =
                ($scope.selectedGender === null || typeof $scope.selectedGender === 'undefined') ? '' : $scope.selectedGender;
            newCustomerInformation.group =
                ($scope.selectedCustomerType.typeName === null ||
            typeof $scope.selectedCustomerType.typeName === 'undefined') ? '' : $scope.selectedCustomerType.typeName;

            Customer.processRegistration($http, newCustomerInformation)
                .success(function() {
                    customerInformation.setCustomerType($scope.selectedCustomerType.typeName);
                    customerInformation.setCustomerLogin($scope.login);
                    $rootScope.customerType = $scope.selectedCustomerType.typeName;
                    if ($scope.selectedCustomerType.typeName === "REGULAR") {
                        $window.location.href = "http://" + $window.location.host + '/#/add_vehicle';
                    } else if ($scope.selectedCustomerType.typeName === "BUSINESS") {
                        $window.location.href = "http://" + $window.location.host + '/#/add_gasstation';
                    }
                })
                .error(function(data) {
                    displayErrorMessages(data);
                });
        };
    }
]);

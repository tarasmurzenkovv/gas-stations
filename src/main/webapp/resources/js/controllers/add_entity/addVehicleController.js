gasStation.controller('AddVehicleController', ['$scope', '$http', '$window', 'customerInformation', 'Vehicle',
    function($scope, $http, $window, customerInformation, Vehicle) {
        var newVehicle = {
            volume: '',
            vehicleType: '',
            registrationNumber: '',
            customerLogin: ''
        };
        $scope.customerLogin = customerInformation.getCustomerLogin();
        $scope.customerType = customerInformation.getCustomerType();
        $scope.isBusiness = function() {
            return $scope.customerType === 'BUSINESS';
        };
        $scope.isRegular = function() {
            return $scope.customerType === 'REGULAR';
        };

        angular.element(document).ready(function() {
            Vehicle.getVehicleTypes($http)
                .success(function(data) {
                    $scope.vehicleTypes = data;
                })
                .error(function(data) {
                    displayErrorMessages(data);
                });
        });
        $scope.SendVehicleData = function() {
            newVehicle.volume = $scope.volume;
            newVehicle.carType = $("#carTypes option:selected").text();
            newVehicle.registrationNumber = $scope.registrationNumber;
            newVehicle.customerLogin = customerInformation.getCustomerLogin();
            Vehicle.sendVehicleData($http, newVehicle)
                .success(function() {
                    $window.location.href = "http://" + $window.location.host + "/pages/index.html#/view_vehicles";
                })
                .error(function(data) {
                    $window.location.href = "http://" + $window.location.host + "/pages/index.html#/add_vehicle";
                    displayErrorMessages(data);
                });
        };
    }
]);

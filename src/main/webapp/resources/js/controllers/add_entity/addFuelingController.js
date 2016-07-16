gasStation.controller('AddFuelingController', ['$scope', '$http', '$window', 'customerInformation', 'Fueling',
    function ($scope, $http, $window, customerInformation, Fueling) {
        var newFuelingInformation = {
            volume: '',
            price: '',
            date: '',
            comment: '',
            fuelType: '',
            vehicleRegistrationNumber: '',
            gasStationName: '',
            rating: '',
            login: ''
        };
        $scope.customerLogin = customerInformation.getCustomerLogin();
        $scope.customerType = customerInformation.getCustomerType();
        $scope.isBusiness = function () {
            return $scope.customerType === 'BUSINESS';
        };
        $scope.isRegular = function () {
            return $scope.customerType === 'REGULAR';
        };
        angular.element(document).ready(function () {
            Fueling.getFuelingData($http)
                .success(function (data) {
                    $scope.fuelTypes = data.fuel_types;
                    $scope.gasStations = data.gas_stations;
                    $scope.customerVehicles = data.vehicles;
                })
                .error(function (data) {
                    displayErrorMessages(data);
                });
        });
        $scope.AddFueling = function () {
            newFuelingInformation.volume = $scope.volume;
            newFuelingInformation.price = $scope.price;
            newFuelingInformation.date = $scope.date;
            newFuelingInformation.comment = $scope.comment;
            newFuelingInformation.fuelType = ($scope.fuelType == ''|| typeof $scope.fuelType === 'undefined') ? null : $scope.fuelType.typeName;
            newFuelingInformation.vehicleRegistrationNumber = ($scope.fuelType == ''||typeof $scope.vehicle === 'undefined') ? null : $scope.vehicle.registrationNumber;
            newFuelingInformation.gasStationName = ($scope.fuelType == ''||typeof $scope.gasStation === 'undefined') ? '' : $scope.gasStation.name;
            newFuelingInformation.rating = $scope.rating;

            Fueling.sendFuelingData($http, newFuelingInformation)
                .success(function () {
                    $window.location.href = "http://" + $window.location.host + '/pages/index.html#/view_fuelings';
                })
                .error(function (data) {
                    $window.location.href = "http://" + $window.location.host + '/pages/index.html#/add_fueling';
                    displayErrorMessages(data);
                });
        };
    }]);

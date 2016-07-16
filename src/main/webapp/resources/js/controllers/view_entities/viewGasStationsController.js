gasStation.controller('ViewGasStationsController', ['$scope', '$http', '$window', 'customerInformation',
    'gasStationInformation','GasStation',
    function($scope, $http, $window, customerInformation, gasStationInformation, GasStation) {
        $scope.gasStation = {
            name: '',
            address: '',
            companyName: ''
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
            GasStation.viewGasStations($http)
                .success(function(data) {
                    if (data.length <= 0) {
                        $scope.forceToEnterGasStations = true;
                    } else {
                        $scope.forceToEnterGasStations = false;
                        $scope.customerGasStations = data;
                    }
                })
                .error(function(data) {
                    displayErrorMessages(data);
                })
        });

        $scope.UpdateGasStation = function(gasStationId) {
            var url = "http://" + $window.location.host + '/pages/index.html#';
            GasStation.updateGasStation($http, gasStationId)
                .success(function(data) {
                    gasStationInformation.setName(data.name);
                    gasStationInformation.setAddress(data.address);
                    gasStationInformation.setCompanyName(data.companyName);
                    gasStationInformation.setId(data.id);
                    $window.location.href = url + '/update_gasstation';
                })
                .error(function(data) {
                    displayErrorMessages(data);
                });
        };
    }
]);

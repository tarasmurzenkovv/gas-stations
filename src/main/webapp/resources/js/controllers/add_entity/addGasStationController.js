gasStation.controller('AddGasStationController', ['$scope', '$http', '$window', 'customerInformation', 'GasStation',

    function($scope, $http, $window, customerInformation, GasStation) {

        var gasStation = {
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

        $scope.AddGasStation = function() {
        
            gasStation.name = $scope.gasStationName;
            gasStation.address = $scope.address;
            gasStation.companyName = $scope.trademark;

            GasStation.sendGasStationData($http, gasStation)
                .success(function() {
                    $window.location.href = "http://" + $window.location.host + '/pages/index.html#/view_gasstations';
                })
                .error(function(data) {
                    $window.location.href = "http://" + $window.location.host + '/pages/index.html#/add_gasstation';
                    displayErrorMessages(data);
                });
        };
    }
]);

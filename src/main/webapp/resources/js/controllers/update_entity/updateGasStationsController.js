gasStation.controller('UpdateGasStationsController', ['$scope', '$http', '$window', 'customerInformation',
    'gasStationInformation','GasStation',
    function ($scope, $http, $window, customerInformation, gasStationInformation, GasStation) {
        var updatedGasStationInformation = {
            name: '',
            address: '',
            companyName: '',
            id: '',
            customerLogin: ''
        };

        $scope.name = gasStationInformation.getName();
        $scope.address = gasStationInformation.getAddress();
        $scope.trademark = gasStationInformation.getCompanyName();

        $scope.customerLogin = customerInformation.getCustomerLogin();
        $scope.customerType = customerInformation.getCustomerType();
        $scope.isBusiness = function () {
            return $scope.customerType === 'BUSINESS';
        };
        $scope.isRegular = function () {
            return $scope.customerType === 'REGULAR';
        };

        $scope.SubmitUpdatedInfo = function () {
            updatedGasStationInformation.name = $scope.name;
            updatedGasStationInformation.address = $scope.address;
            updatedGasStationInformation.companyName = $scope.trademark;
            updatedGasStationInformation.id = gasStationInformation.getId();

            GasStation.submitUpdatedInformation($http,updatedGasStationInformation)
                .success(function () {
                    $window.location.href = "http://" + $window.location.host + '/pages/index.html#/view_gasstations';
                })
                .error(function (data) {
                    $window.location.href = "http://" + $window.location.host + '/pages/index.html#/update_gasstation';
                    displayErrorMessages(data);
                });
        };
    }]);

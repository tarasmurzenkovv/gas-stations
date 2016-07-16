gasStation.controller('RevenueCalculation', ['$scope', '$http', '$filter', 'customerInformation',
    function ($scope, $http, $filter, customerInformation) {
        var revenueParameters = {
            from: '',
            to: '',
            gasStationName: ''
        };


        $scope.displayRevenue;
        $scope.customerLogin = customerInformation.getCustomerLogin();
        $scope.customerType = customerInformation.getCustomerType();

        $scope.isBusiness = function () {
            return $scope.customerType === 'BUSINESS';
        };
        $scope.isRegular = function () {
            return $scope.customerType === 'REGULAR';
        };
        $scope.customerGasStations = {};

        angular.element(document).ready(function () {
            $http({
                method: 'GET',
                url: "/view_gasstations"
            })
                .success(function (data) {
                    $scope.customerGasStations = data;
                })
                .error(function (data) {
                    displayErrorMessages(data);
                });
        });

        $scope.ProcessRevenueRequest = function () {

            revenueParameters.from = $filter('date')($scope.from, 'yyyy-MM-dd');
            revenueParameters.to = $filter('date')($scope.to, 'yyyy-MM-dd');
            revenueParameters.gasStationName = ($scope.selectedGasStation === null || typeof $scope.selectedGasStation === 'undefined') ? '' : $scope.selectedGasStation.name;

            $http({
                method: 'get',
                url: '/calculate_revenue?from=' + revenueParameters.from + '&to=' + revenueParameters.to + '&name=' + revenueParameters.gasStationName
            })
                .success(function (data) {
                    $scope.customerRevenues = data;
                    document.getElementById('globalServerError').innerText = '';
                    if (jQuery.isEmptyObject($scope.customerRevenues)) {
                        $scope.message = "No fuelings were made within period";
                        $scope.displayRevenue = false;
                    } else {
                        $scope.message = "";
                        $scope.displayRevenue = true;
                    }
                })
                .error(function (data) {
                    $scope.message = "";
                    $scope.displayRevenue = false;
                    displayErrorMessages(data);
                });
        };
    }]);

gasStation.controller('SearchGasStations', ['$scope', '$http', 'customerInformation', 'Fueling',
    function ($scope, $http, customerInformation, Fueling) {

        $scope.fuelTypes = null;
        $scope.showTable = false;
        $scope.customerLogin = customerInformation.getCustomerLogin();
        $scope.customerType = customerInformation.getCustomerType();
        $scope.isBusiness = function () {
            console.log(customerInformation.getCustomerType());
            return $scope.customerType === 'BUSINESS';
        };
        $scope.isRegular = function () {
            return $scope.customerType === 'REGULAR';
        };

        angular.element(document).ready(function () {
            Fueling.getFuelTypes($http)
                .success(function (data) {
                    $scope.fuelTypes = data;
                })
                .error(function (data) {
                    displayErrorMessages(data);
                });
        });

        $scope.SearchGasStations = function () {
            var fuelTypeName = (($scope.selectedFuelType == null) || (typeof $scope.selectedFuelType === 'undefined'))
                ? '' : $scope.selectedFuelType.typeName;
            $http({
                method: 'get',
                url: '/search_gasstations?name=' + fuelTypeName
            })
                .success(function (data) {
                    document.getElementById("globalServerError").innerHTML = "";
                    $scope.showTable = true;
                    $scope.rated = (data.rated === null) ? 'N/A' : data.rated.companyName;
                    $scope.cheapest = (data.cheapest === null) ? 'N/A' : data.cheapest.companyName;
                    $scope.popular = (data.popular === null) ? 'N/A' : data.popular.companyName;
                })
                .error(function (data) {
                    $scope.showTable = false;
                    displayErrorMessages(data);
                });
        };
    }]);

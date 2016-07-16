gasStation.controller('ViewVehiclesController', ['$scope', '$http', '$window', 'customerInformation', 'Vehicle',
    function ($scope, $http, $window, customerInformation, Vehicle) {
        $scope.customerLogin = customerInformation.getCustomerLogin();
        $scope.customerType = customerInformation.getCustomerType();
        $scope.isBusiness = function () {
            return $scope.customerType === 'BUSINESS';
        };
        $scope.isRegular = function () {
            return $scope.customerType === 'REGULAR';
        };

        angular.element(document).ready(function () {
                Vehicle.viewVehicles($http)
                .success(function (data) {
                    if (data.length <= 0) {
                        $scope.forceToEnterVehicles = true;
                    } else {
                        $scope.forceToEnterVehicles = false;
                        $scope.customerVehicles = data;
                    }
                })
                .error(function (data) {
                    displayErrorMessages(data);
                });
        });

        $scope.deleteVehicle = function (vehicleId) {
            $("#dialog-confirm").dialog({
                resizable: false,
                modal: true,
                width: 'auto',
                height:'auto',
                buttons: {
                    "Delete vehicle": function () {
                        Vehicle.deleteVehicle($http,vehicleId)
                            .success(function () {
                                for (var i = 0; i < $scope.customerVehicles.length; i++) {
                                    if ($scope.customerVehicles[i].id === vehicleId) {
                                        $scope.customerVehicles.splice(i, 1);
                                    }
                                }
                                $scope.forceToEnterVehicles = $scope.customerVehicles.length <= 0;
                            })
                            .error(function (data) {
                                displayErrorMessages(data);
                            });
                        $(this).dialog("close");
                    },
                    "Cancel": function () {
                        $(this).dialog("close");
                    }
                }
            });
        };
}]);
gasStation.controller('LoginController', ['$rootScope', '$scope', '$http', '$window', 'customerInformation', 'Customer',
    function ($rootScope, $scope, $http, $window, customerInformation, Customer) {
        $rootScope.Login = function () {
            $rootScope.errorMessage = null;
            Customer.login($http, $scope.username, $scope.password)
                .success(function (response) {
                    $rootScope.customerType = response.customerType;
                    customerInformation.setCustomerType(response.customerType);
                    customerInformation.setCustomerLogin(response.login);
                    $rootScope.isBusiness = function () {
                        return $scope.customerType === 'BUSINESS';
                    };
                    $rootScope.isRegular = function () {
                        return $scope.customerType === 'REGULAR';
                    };

                    $scope.errorMessage = null;
                    if (response.customerType === 'REGULAR') {
                        if (response.numberOfEntries === 0) {
                            $window.location.href = "http://" + $window.location.host + '/#/add_vehicle';
                        } else {
                            $window.location.href = "http://" + $window.location.host + '/#/view_vehicles';
                        }
                    } else {
                        if (response.numberOfEntries === 0) {
                            $window.location.href = "http://" + $window.location.host + '/#/add_gasstation';
                        } else {
                            $window.location.href = "http://" + $window.location.host + '/#/view_gasstations';
                        }
                    }
                })
                .error(function (response) {
                    $rootScope.errorMessage = response;
                });
        };
    }]);

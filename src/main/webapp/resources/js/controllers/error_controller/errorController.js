gasStation.controller('ErrorController', ['$rootScope', '$scope','customerInformation','$cookies',
        function ($rootScope, $scope, customerInformation,$cookies) {
            var customerTypeFromCookie = $cookies.get('GasStationCookie');
            console.log("From error controller, customer type: "+customerInformation.getCustomerType());
            $scope.customerType = customerTypeFromCookie;
            $scope.isBusiness = function () {
                return $scope.customerType === 'BUSINESS';
            };
            $scope.isRegular = function () {
                return $scope.customerType === 'REGULAR';
            };
    }]);
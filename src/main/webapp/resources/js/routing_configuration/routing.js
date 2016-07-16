//noinspection JSUnresolvedFunction
gasStation.config(['$routeProvider', '$httpProvider', '$locationProvider',
    function ($routeProvider) {
        //$httpProvider.interceptors.push('responseObserver');
        $routeProvider
            .when('/', {
                templateUrl: '/pages/public/landing/landing.html',
                controller: 'MainController'
            })
            .when('/login', {
                templateUrl: '/pages/public/security/login.html',
                controller: 'LoginController'
            })
            .when('/register', {
                templateUrl: '/pages/public/security/registration.html',
                controller: 'RegistrationController'
            })
            .when('/logout', {
                templateUrl: '/pages/public/security/logout.html',
                controller: 'LogoutController'
            })
            .when('/add_gasstation', {
                templateUrl: '/pages/private/add_entity/add_gasstation.html',
                controller: 'AddGasStationController',
                resolve: {
                    validate: function ($q, $location, $rootScope) {
                        var validateAccess = $q.defer();
                        if ($rootScope.customerType === 'REGULAR') {
                            $location.path('/access_denied');
                        } else if ($rootScope.customerType === null || typeof $rootScope.customerType === 'undefined') {
                            $location.path('/access_unauthorized');
                        }
                        validateAccess.resolve();
                        return validateAccess.promise;
                    }
                }
            })
            .when('/add_vehicle', {
                templateUrl: '/pages/private/add_entity/add_vehicle.html',
                controller: 'AddVehicleController',
                resolve: {
                    validate: function ($q, $location, $rootScope) {
                        var validateAccess = $q.defer();
                        if ($rootScope.customerType === 'BUSINESS') {
                            $location.path('/access_denied');
                        } else if ($rootScope.customerType === null || typeof $rootScope.customerType === 'undefined') {
                            $location.path('/access_unauthorized');
                        }
                        validateAccess.resolve();
                        return validateAccess.promise;
                    }
                }
            })
            .when('/add_fueling', {
                templateUrl: '/pages/private/add_entity/add_fueling.html',
                controller: 'AddFuelingController',
                resolve: {
                    validate: function ($q, $location, $rootScope) {
                        var validateAccess = $q.defer();
                        if ($rootScope.customerType === 'BUSINESS') {
                            $location.path('/access_denied');
                        } else if ($rootScope.customerType === null || typeof $rootScope.customerType === 'undefined') {
                            $location.path('/access_unauthorized');
                        }

                        validateAccess.resolve();
                        return validateAccess.promise;
                    }
                }
            })
            .when('/search_gasstations', {
                templateUrl: '/pages/private/service/search_gasstation.html',
                controller: 'SearchGasStations',
                resolve: {
                    validate: function ($q, $location, $rootScope) {
                        var validateAccess = $q.defer();
                        if ($rootScope.customerType === 'BUSINESS') {
                            $location.path('/access_denied');
                        } else if ($rootScope.customerType === null || typeof $rootScope.customerType === 'undefined') {
                            $location.path('/access_unauthorized');
                        }
                        validateAccess.resolve();
                        return validateAccess.promise;
                    }
                }
            })
            .when('/revenue_calculation', {
                templateUrl: '/pages/private/service/revenue_calculation.html',
                controller: 'RevenueCalculation',
                resolve: {
                    validate: function ($q, $location, $rootScope) {
                        var validateAccess = $q.defer();
                        if ($rootScope.customerType === 'REGULAR') {
                            $location.path('/access_denied');
                        } else if ($rootScope.customerType === null || typeof $rootScope.customerType === 'undefined') {
                            $location.path('/access_unauthorized');
                        }

                        validateAccess.resolve();
                        return validateAccess.promise;
                    }
                }
            })
            .when('/dashboard', {
                templateUrl: '/pages/private/dashboard/dashboard.html',
                controller: 'DashboardController'
            })
            .when('/view_fuelings', {
                templateUrl: '/pages/private/view_entities/view_fuelings.html',
                controller: 'ViewFuelingsController',
                resolve: {
                    validate: function ($q, $location, $rootScope) {
                        var validateAccess = $q.defer();
                        if ($rootScope.customerType === 'BUSINESS') {
                            $location.path('/access_denied');
                        } else if ($rootScope.customerType === null || typeof $rootScope.customerType === 'undefined') {
                            $location.path('/access_unauthorized');
                        }

                        validateAccess.resolve();
                        return validateAccess.promise;
                    }
                }
            })
            .when('/view_vehicles', {
                templateUrl: '/pages/private/view_entities/view_vehicles.html',
                controller: 'ViewVehiclesController',
                resolve: {
                    validate: function ($q, $location, $rootScope) {
                        var validateAccess = $q.defer();
                        if ($rootScope.customerType === 'BUSINESS') {
                            $location.path('/access_denied');
                        } else if ($rootScope.customerType === null || typeof $rootScope.customerType === 'undefined') {
                            $location.path('/access_unauthorized');
                        }

                        validateAccess.resolve();
                        return validateAccess.promise;
                    }
                }
            })
            .when('/view_gasstations', {
                templateUrl: '/pages/private/view_entities/view_gasstations.html',
                controller: 'ViewGasStationsController',
                resolve: {
                    validate: function ($q, $location, $rootScope) {
                        var validateAccess = $q.defer();
                        if ($rootScope.customerType === 'REGULAR') {
                            $location.path('/access_denied');
                        } else if ($rootScope.customerType === null || typeof $rootScope.customerType === 'undefined') {
                            $location.path('/access_unauthorized');
                        }

                        validateAccess.resolve();
                        return validateAccess.promise;
                    }
                }
            })
            .when('/update_gasstation', {
                templateUrl: '/pages/private/update_entity/update_gasstation.html',
                controller: 'UpdateGasStationsController',
                resolve: {
                    validate: function ($q, $location, $rootScope) {
                        var validateAccess = $q.defer();
                        if ($rootScope.customerType === 'REGULAR') {
                            $location.path('/access_denied');
                        } else if ($rootScope.customerType === null || typeof $rootScope.customerType === 'undefined') {
                            $location.path('/access_unauthorized');
                        }

                        validateAccess.resolve();
                        return validateAccess.promise;
                    }
                }
            })
            .when('/access_denied', {
                templateUrl: '/pages/public/security/access_denied.html',
                controller: 'ErrorController',
                resolve: {
                    customerType: function ($rootScope) {
                        return $rootScope.customerType;
                    }
                }
            })
            .when('/access_unauthorized', {
                templateUrl: '/pages/public/security/access_unauthorized.html'
            })
    }
]);

// intercept any error responses from a server and render an appropriate view.
gasStation.factory('responseObserver', function responseObserver($q, $window) {
    return {
        responseError: function (errorResponse) {
            if ($window.location.hash !== '#/login') {
                if (errorResponse.status === 401) {
                    $window.location.href = "http://" + $window.location.host + '/pages/index.html#' + '/access_unauthorized.html';
                }
            }
            return $q.reject(errorResponse);
        }
    };
});

gasStation.controller('ViewFuelingsController', ['$scope', '$http', 'customerInformation',
    function ($scope, $http, customerInformation) {
        $scope.currentPage = 0;
        $scope.maxPages = 1;
        $scope.customerFuelings = {};

        $scope.customerLogin = customerInformation.getCustomerLogin();
        $scope.customerType = customerInformation.getCustomerType();
        $scope.isBusiness = function () {
            return $scope.customerType === 'BUSINESS';
        };
        $scope.isRegular = function () {
            return $scope.customerType === 'REGULAR';
        };

        angular.element(document).ready(function () {
            $http({
                method: 'GET',
                url: "/view_fuelings?page=" + $scope.currentPage
            })
                .success(function (data) {
                    console.log("Success !");
                    if (data.length === 0) {
                        $scope.forceToEnterFuelings = true;
                    } else {
                        $scope.forceToEnterFuelings = false;
                        $scope.customerFuelings = data;
                    }
                })
                .error(function (data) {
                    displayErrorMessages(data);
                });

            $http({
                method: 'GET',
                url: "/number_of_pages"
            })
                .success(function (data) {
                    $scope.maxPages = data;
                })
                .error(function (data) {
                    console.log("BS !");
                });
        });
        $scope.deleteFueling = function (fuelingId) {
            $("#dialog-confirm").dialog({
                resizable: false,
                height: 140,
                modal: true,
                width: 'auto',
                height: 'auto',
                buttons: {
                    "Delete fueling": function () {
                        $http({
                            method: 'DELETE',
                            url: '/delete_fueling?id=' + fuelingId
                        })
                            .success(function () {
                                $http({
                                    method: 'GET',
                                    url: "/view_fuelings?page=0"
                                })
                                    .success(function (data) {
                                        console.log("Success !");
                                        if (data.length === 0) {
                                            $scope.forceToEnterFuelings = true;
                                        } else {
                                            $scope.forceToEnterFuelings = false;
                                            $scope.customerFuelings = data;
                                            $http({
                                                method: 'GET',
                                                url: "/number_of_pages"
                                            })
                                                .success(function (data) {
                                                    $scope.maxPages = data;
                                                })
                                                .error(function (data) {
                                                    displayErrorMessages(data);
                                                });
                                        }
                                    })
                                    .error(function (data) {
                                        displayErrorMessages(data);
                                    });
                            })
                            .error(function () {
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

        $scope.prevPortion = function () {
            $scope.currentPage--;
            $http({
                method: 'GET',
                url: "/view_fuelings?page=" + $scope.currentPage
            })
                .success(function (data) {
                    $scope.customerFuelings = data;
                })
                .error(function (data) {
                    displayErrorMessages(data);
                });
        };
        $scope.nextPortion = function () {
            $scope.currentPage++;
            $scope.displayNext =
                $http({
                    method: 'GET',
                    url: "/view_fuelings?page=" + $scope.currentPage
                })
                    .success(function (data) {
                        $scope.customerFuelings = data;
                    })
                    .error(function (data) {
                        displayErrorMessages(data);
                    });
        };
    }]);
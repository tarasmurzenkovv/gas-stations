gasStation.service('GasStation', [function() {
    this.sendGasStationData = function($http, gasStationData) {
        return $http({
            method: 'POST',
            url: '/add_gasstation',
            data: gasStationData
        })
    };

    this.viewGasStations = function($http){
        return $http({
            method: 'GET',
            url: "/view_gasstations"
        })
    };

    this.updateGasStation = function($http, gasStationId){
        return $http({
            method: 'POST',
            url: "/update?id=" + gasStationId
        })
    };

    this.submitUpdatedInformation= function($http, newGasStationInformation){
        return $http({
            method: 'POST',
            url: "/update",
            data: newGasStationInformation
        })
    };
}]);
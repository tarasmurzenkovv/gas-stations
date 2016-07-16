gasStation.service('Fueling', [function() {
    this.sendFuelingData = function($http, fuelingData) {
        return $http({
            method: 'POST',
            url: '/add_fueling',
            data: fuelingData
        })
    };
    this.getFuelingData = function($http) {
        return $http({
            method: 'get',
            url: '/fetch_data'
        })
    };
    this.deleteFueling = function($http, fuelingId) {
        return $http({
            method: 'delete',
            url: '/add_fueling?id = ' + fuelingId
        })
    };
    this.getFuelTypes = function($http){
        return $http({
            method: 'GET',
            url: "/get_all_fuel_types"
        })
    };
}]);

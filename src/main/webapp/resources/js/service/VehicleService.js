gasStation.service('Vehicle', [function() {
    this.sendVehicleData = function($http, vehicleData) {
        return $http({
            method: 'POST',
            url: '/add_vehicle',
            data: vehicleData
        })
    };
    this.getVehicleTypes = function ($http) {
        return $http({
            method: 'get',
            url: '/vehicle_types'
        })
    };
    this.deleteVehicle = function($http, vehicleId){
        return $http({
            method: 'delete',
            url: '/delete_vehicle?id='+vehicleId
        })
    };
    this.viewVehicles = function($http){
        return $http({
            method: 'GET',
            url: '/view_vehicles'
        })
    };
}]);
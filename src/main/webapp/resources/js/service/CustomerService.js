gasStation.service('Customer', [function() {
    this.getGenderGroups= function ($http) {
        return $http({
            method: 'get',
            url: '/gender_groups'
        })
    };
    this.getCustomerTypes = function ($http) {
        return $http({
            method: 'get',
            url: '/customer_types'
        })
    };
    this.processRegistration = function($http, customerData){
        return $http({
            method: 'POST',
            url: '/register',
            data: customerData
        })
    };
    this.login = function($http, login, password){
        $http.defaults.headers.common['Authorization'] = 'Basic ' + btoa(login + ':' + password);
        return $http({
            method: 'POST',
            url: '/ajax_login'/*,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                "X-Login-Ajax-call": 'true'
            }*/
        })
    };
}]);
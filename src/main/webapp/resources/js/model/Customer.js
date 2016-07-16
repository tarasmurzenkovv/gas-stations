gasStation.factory('customerInformation', function () {
    var customerType;
    var customerLogin;
    return {
        getCustomerLogin: function () {
            return customerLogin;
        },
        setCustomerLogin: function (login) {
            customerLogin = login
        },
        getCustomerType: function () {
            return customerType;
        },
        setCustomerType: function (type) {
            customerType = type;
        }
    };
});
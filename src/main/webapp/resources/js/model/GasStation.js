gasStation.factory('gasStationInformation', function () {
    var name;
    var address;
    var companyName;
    var id;

    return {
        getName: function () {
            return name;
        },
        setName: function (type) {
            name = type;
        },
        getAddress: function () {
            return address;
        },
        setAddress: function (addressToSet) {
            address = addressToSet;
        },
        getCompanyName: function () {
            return companyName;
        },
        setCompanyName: function (companyNameToSet) {
            companyName = companyNameToSet;
        },
        getId: function () {
            return id;
        },
        setId: function (idToSet) {
            id = idToSet;
        }
    };
});


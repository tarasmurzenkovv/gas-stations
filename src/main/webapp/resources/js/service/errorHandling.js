var errorLabels = ["globalServerError", "firstNameError", "lastNameError", "loginError", "passwordError", "emailError",
    "genderError", "groupError", "dateOfBirthError", "nameError", "addressError", "companyNameError", "volumeError",
    "carTypeError", "dateError", "priceError", "vehicleRegistrationNumberError", "gasStationNameError", "fuelTypeError",
    "ratingError", "fromError", "toError", "gasStationNameError", "registrationNumberError"
];

function displayErrorMessages(data) {
    var errors = data;
    errorLabels.forEach(function(errorLabel) {
        var element = document.getElementById(errorLabel);
        if (element !== null) {
            if (errors.hasOwnProperty(errorLabel)) {
                element.innerHTML = errors[errorLabel];
            } else {
                element.innerHTML = '';
            }
        }
    })
}

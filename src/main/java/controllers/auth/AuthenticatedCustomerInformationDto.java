package controllers.auth;


/**
 * This object sends the data based on which, a front-end client will render a particular view.
 * I.e. if a customer is a regular and he/she hasn't submitted any vehicle information
 * then the front end client will forward to the page 'Add vehicle' after a customer has passed a successful authorization
 *
 * The object contains the following fields:
 *
 * customerType - hold the type of a registered customer, either REGULAR or BUSINESS
 * numberOfSubmittedEntries - 0 or any positive integer number of gas stations (for business type of a customer)
 * or vehicles (for regular type of a customer)
 * status - contains value 'ok' if authorization has been passed successfully
 *
 */
public class AuthenticatedCustomerInformationDto {
    String login;
    String customerType;
    String status;
    int numberOfEntries;

    public AuthenticatedCustomerInformationDto() {
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    public void setNumberOfEntries(int numberOfEntries) {
        this.numberOfEntries = numberOfEntries;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
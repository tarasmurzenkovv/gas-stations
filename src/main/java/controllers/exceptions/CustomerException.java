package controllers.exceptions;

import model.CustomerType;

public class CustomerServiceException extends GeneralServerException {
    private CustomerType customerType;

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public CustomerServiceException(CustomerType customerType, String message) {
        super(message);
        this.customerType = customerType;
    }

    public CustomerServiceException(String message) {
        super(message);
    }
}

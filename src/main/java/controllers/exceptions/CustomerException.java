package controllers.exceptions;

import model.type.CustomerType;

public class CustomerException extends RuntimeException {
    private CustomerType customerType;

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public CustomerException(String message) {
        super(message);
    }
}

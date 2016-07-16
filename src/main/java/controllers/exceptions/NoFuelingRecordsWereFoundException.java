package controllers.exceptions;

public class NoFuelingRecordsWereFoundException extends GeneralServerException {
    int customerId;

    public NoFuelingRecordsWereFoundException(String message) {
        super(message);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}

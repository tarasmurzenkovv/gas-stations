package controllers.vehicle;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class VehicleDto {
    @NotNull(message = "Enter engine volume")// see the corresponding setter
    private Double volume;

    @NotNull(message = "Enter registration number")// see the corresponding setter
    @Pattern(regexp = "[A-Z]{2}[0-9]{4}[A-Z]{2}", message = "Invalid format of the registration number")
    private String registrationNumber;

    @NotNull(message = "Select car type")// see the corresponding setter
    private String carType;

    public VehicleDto() {
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = (volume <= 0.0) ? null : volume;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = StringUtils.isEmpty(registrationNumber) ? null : registrationNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = StringUtils.isEmpty(carType) ? null : carType;
    }
}

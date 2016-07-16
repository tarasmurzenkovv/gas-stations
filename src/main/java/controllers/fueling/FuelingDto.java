package controllers.fueling;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class FuelingDto {

    private int id;

    @NotNull(message = "Enter volume bought")
    private Double volume;

    @NotNull(message = "Enter price")
    private BigDecimal price;

    @NotNull(message = "Enter date")
    private Date date;

    private String comment;

    @NotNull(message = "Select fuel type")
    private String fuelType;

    @NotNull(message = "Select vehicle")
    private String vehicleRegistrationNumber;

    @NotNull(message = "Select gas station")
    private String gasStationName;

    @NotNull(message = "Place a rating")
    private Integer rating;

    public FuelingDto() {
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = StringUtils.isEmpty(fuelType) ? null : fuelType;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = StringUtils.isEmpty(vehicleRegistrationNumber) ? null : vehicleRegistrationNumber;
    }

    public String getGasStationName() {
        return gasStationName;
    }

    public void setGasStationName(String gasStationName) {
        this.gasStationName = StringUtils.isEmpty(gasStationName) ? null : gasStationName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

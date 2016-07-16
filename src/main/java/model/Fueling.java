package model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "fueling")
public class Fueling implements Serializable {

    @Id
    @GeneratedValue // default AUTO.
    @Column(name = "id")
    private Integer id = new Integer(0);

    @Min(1)
    @NotNull(message = "Enter volume bought")
    @Column(name = "volume", nullable = false)
    private double volume;

    @NotNull(message = "Enter price")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @NotNull(message = "Enter date of purchase")
    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "fuel_type", foreignKey = @ForeignKey(name = "fuel_type"))
    private FuelType fuelType;

    @ManyToOne
    @JoinColumn(name = "vehicle", foreignKey = @ForeignKey(name = "vehicle"))
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "gas_station", foreignKey = @ForeignKey(name = "gas_station"))
    private GasStation gasStation;

    @NotNull(message = "Select rating")
    @Column(name = "rating", nullable = false)
    private int rating;

    @Column
    private boolean isDeleted;

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Fueling() {
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {

        return rating;
    }

    public GasStation getGasStation() {
        return gasStation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setGasStation(GasStation gasStation) {
        this.gasStation = gasStation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

}

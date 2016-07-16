package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue // default AUTO.
    @Column (name = "id")
    private Integer id = new Integer(0);

    @NotNull(message = "Enter volume")
    @Column (name = "volume", nullable = false)
    private double volume;

    @Column(name = "deleted", nullable = false)
    private boolean isDeleted;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "cartype")
    private CarType carType;

    @NotEmpty(message = "Enter registration number")
    @Column(name = "number", nullable = false)
    private String registrationNumber;

    @JsonIgnore
    @OneToMany (mappedBy = "vehicle")
    private List<Fueling> fuelings = new ArrayList<>();

    //<editor-fold desc="Default constructor, getters and setters">
    public Vehicle(){
        isDeleted = false;
    }

    public void addFueling(Fueling fueling){
        fueling.setVehicle(this);
        fuelings.add(fueling);
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public List<Fueling> getFuelings() {
        return fuelings;
    }

    public void setFuelings(List<Fueling> fuelings) {
        this.fuelings = fuelings;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}

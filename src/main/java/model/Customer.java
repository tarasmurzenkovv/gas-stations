package model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue // default AUTO.
    @Column(name = "id")
    private Integer id = new Integer(0);

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @NotEmpty(message = "Enter name")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "Enter last name")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotEmpty(message = "Enter e-mail")
    @Column(name = "e_mail")
    @Email
    private String email;

    @NotNull(message = "Enter date of birth")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotEmpty(message = "Enter your login")
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NotEmpty(message = "Enter password")
    @Size(min = 5, message = "Password must be more than 5")
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    private CustomerType customerType;

    @OneToMany(mappedBy = "customer")
    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<GasStation> gasstations = new ArrayList<>();

    public Customer() {
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<GasStation> getGasstations() {
        return gasstations;
    }

    public void setGasstations(List<GasStation> gasstations) {
        this.gasstations = gasstations;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", enabled=" + enabled +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", customerType=" + customerType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!firstName.equals(customer.firstName)) return false;
        if (!lastName.equals(customer.lastName)) return false;
        if (!email.equals(customer.email)) return false;
        if (gender != customer.gender) return false;
        if (!login.equals(customer.login)) return false;
        return customerType.equals(customer.customerType);

    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + customerType.hashCode();
        return result;
    }
}
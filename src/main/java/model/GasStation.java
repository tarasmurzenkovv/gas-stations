package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import service.Revenue;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gas_station")
@SqlResultSetMapping(
        name = "revenue",
        classes = {
                @ConstructorResult(
                        targetClass = Revenue.class,
                        columns = {
                                @ColumnResult(name = "date"),
                                @ColumnResult(name = "revenue")
                        }
                )
        }
)
@NamedNativeQuery(name = "calculateRevenueForGasStationName",
        query = "select fueling.date, fueling.price*fueling.volume as revenue from gas_station " +
                "left join fueling " +
                "on gas_station.id = fueling.gas_station " +
                "where fueling.date between ?1 and ?2 and gas_station.name=?3 " +
                "group by fueling.date",
        resultSetMapping = "revenue")
public class GasStation implements Serializable {
    @Id
    @GeneratedValue // default AUTO.
    @Column(name = "id")
    private Integer id = new Integer(0);

    @NotEmpty(message = "Enter name")
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "gasStation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Fueling> fuelings = new ArrayList<>();

    @NotEmpty(message = "Enter trademark")
    @Column(name = "trademark", nullable = false)
    private String companyName;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer")
    private Customer customer;

    @NotEmpty(message = "Enter address")
    @Column(name = "address", nullable = false)
    private String address;

    public GasStation() {
        this.setName("N/A");
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void addFueling(Fueling fueling) {
        fueling.setGasStation(this);
        fuelings.add(fueling);
    }

    public List<Fueling> getFuelings() {
        return fuelings;
    }

    public void setFuelings(List<Fueling> fuelings) {
        this.fuelings = fuelings;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public GasStation setName(String name) {
        this.name = name;
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GasStation that = (GasStation) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(companyName != null ? !companyName.equals(that.companyName) : that.companyName != null);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + companyName.hashCode();
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + address.hashCode();
        return result;
    }
}

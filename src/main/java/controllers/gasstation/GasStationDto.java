package controllers.gasstation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;

public class GasStationDto {

    private Integer id;

    @NotNull(message = "Enter name")
    private String name;

    @NotNull(message = "Enter address")
    private String address;

    @NotNull(message = "Enter trademark")
    private String companyName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.isEmpty(name) ? null : name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = StringUtils.isEmpty(address) ? null : address;
    }

    public String getCompanyName() {
        return companyName;
    }
}

package util;

import model.type.CarType;
import model.type.CustomerType;
import model.type.FuelType;
import model.type.Type;

import java.util.ArrayList;
import java.util.List;

public class TypesBuilder {
    public static List<CarType> getCarTypes() {
        List<CarType> expectedCarTypes = new ArrayList<>();
        CarType toyota = new CarType();
        toyota.setId(1);
        toyota.setTypeName("Toyota");
        CarType mercedes = new CarType();
        mercedes.setId(2);
        mercedes.setTypeName("Mercedes");
        CarType volvo = new CarType();
        volvo.setId(3);
        volvo.setTypeName("Volvo");
        CarType peugeot = new CarType();
        peugeot.setId(4);
        peugeot.setTypeName("Peugeot");
        CarType renault = new CarType();
        renault.setId(5);
        renault.setTypeName("Renault");
        expectedCarTypes.add(toyota);
        expectedCarTypes.add(mercedes);
        expectedCarTypes.add(volvo);
        expectedCarTypes.add(peugeot);
        expectedCarTypes.add(renault);
        return expectedCarTypes;
    }

    public static List<CustomerType> getCustomerTypes() {
        List<CustomerType> customerTypes = new ArrayList<>();

        CustomerType regular = new CustomerType();
        regular.setId(1);
        regular.setTypeName("REGULAR");
        customerTypes.add(regular);

        CustomerType business = new CustomerType();
        business.setId(2);
        business.setTypeName("BUSINESS");
        customerTypes.add(business);

        return customerTypes;
    }

    public static List<Type> getFuelTypes() {
        List<Type> expectedFuelTypes = new ArrayList<>();

        Type a72 = new FuelType();
        a72.setId(1);
        a72.setTypeName("A-72");
        expectedFuelTypes.add(a72);

        Type a76 = new FuelType();
        a76.setId(1);
        a76.setTypeName("A-76");
        expectedFuelTypes.add(a76);

        Type a80 = new FuelType();
        a80.setId(3);
        a80.setTypeName("A-80");
        expectedFuelTypes.add(a80);

        Type ai91 = new FuelType();
        ai91.setId(4);
        ai91.setTypeName("AI-91");
        expectedFuelTypes.add(ai91);

        Type ai93 = new FuelType();
        ai93.setId(5);
        ai93.setTypeName("AI-93");
        expectedFuelTypes.add(ai93);

        Type ai95 = new FuelType();
        ai95.setId(6);
        ai95.setTypeName("AI-95");
        expectedFuelTypes.add(ai95);

        return expectedFuelTypes;
    }
}

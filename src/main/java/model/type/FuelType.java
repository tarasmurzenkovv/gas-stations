package model.type;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table (name = "fuel_type")
public class FuelType extends Type implements Serializable {

}

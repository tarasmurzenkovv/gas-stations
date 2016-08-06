package model.type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "car_type")
@AttributeOverride(name="type_name", column = @Column(name="car_type"))
public class CarType extends Type implements Serializable {
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}

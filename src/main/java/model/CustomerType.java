package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "customer_type")
@AttributeOverride(name="type_name", column = @Column(name="customer_type"))
public class CustomerType extends Type implements Serializable{

}

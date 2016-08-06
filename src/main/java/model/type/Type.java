package model.type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "type")

// each inherited class will mapped per its own table. it wont be possible to query super/root class.
// a table for abstract class wont be created.
@MappedSuperclass
public abstract class Type {
    @Id
    @GeneratedValue

    // default is AUTO i.e. hibernate will choose from SEQUENCE, TABLE or IDENTITY
    // IDENTITY <----> relies on RDBMS to automatically generate the primary key values
    // SEQUENCE <----> allows to provide a custom primary key value generation strategy
    // TABLE    <----> allows to provide a custom table where the pk will stored, highly portable across rich space of RDBMS
    protected Integer id;

    @NotNull
    @Column(name = "type_name")
    protected String typeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + typeName;
    }

    @Override
    public boolean equals(Object o) {
        Type type = (Type) o;

        return typeName.equals(type.typeName);

    }

    @Override
    public int hashCode() {
        return typeName.hashCode();
    }
}

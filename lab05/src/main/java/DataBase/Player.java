package DataBase;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="Players")
public class Player implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name ="incrementator", strategy = "increment")
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name+" id: "+getID();
    }
}

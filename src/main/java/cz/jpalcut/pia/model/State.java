package cz.jpalcut.pia.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "state")
public class State implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

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
        this.name = name;
    }
}

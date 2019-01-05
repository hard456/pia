package cz.jpalcut.pia.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Tabulka rolí uživatelů
 */
@Entity
@Table(schema = "public", name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //jméno role
    @Column(name = "name")
    private String name;

    //seznam uživatelů s touto rolí
    @ManyToMany(mappedBy = "roleList")
    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

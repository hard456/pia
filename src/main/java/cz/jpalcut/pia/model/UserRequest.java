package cz.jpalcut.pia.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Tabulka požadavků uživatelů ke změně
 */
@Entity
@Table(schema = "public", name = "user_request")
public class UserRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //typ požadavku uživatele
    @Nullable
    @Column(name = "type")
    private String type;

    //hodnota ke změnění
    @Nullable
    @Column(name = "value")
    private Double value;

    //účet ke změně
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}

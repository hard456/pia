package cz.jpalcut.pia.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Tabulka uživatelů
 */
@Entity
@Table(schema = "public", name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Size(min = 1, max = 50, message = "Jméno musí mít 1-50 znaků!")
    @NotNull
    @Column(name = "firstname")
    private String firstname;

    @Size(min = 1, max = 50, message = "Příjmení musí mít 1-50 znaků!")
    @NotNull
    @Column(name = "lastname")
    private String lastname;

    @Size(min = 6, max = 50, message = "Email musí mít 6-50 znaků!")
    @NotNull
    @Email(message = "Špatný formát emailu!")
    @Column(name = "email")
    private String email;

    //přihlašovací jméno
    @Nullable
    @Column(name = "login_id")
    private String loginId;

    //přihlašovací heslo
    @Nullable
    @Column(name = "pin")
    private String pin;

    //rodné číslo
    @Size(min = 10, max = 10, message = "Rodné číslo musí mít 10 znaků!")
    @Pattern(regexp = "^[0-9]*$", message = "Rodné číslo musí obsahovat pouze čísla!")
    @NotNull
    @Column(name = "pid")
    private String pid;

    @Size(max = 50, message = "Maximální počet znaků je 50!")
    @NotNull
    @Column(name = "address")
    private String address;

    //číslo popisné
    @Size(max = 8, message = "Maximální počet znaků je 8!")
    @Pattern(regexp = "^[0-9]*$", message = "Číslo popisné musí obsahovat pouze čísla!")
    @NotNull
    @Column(name = "address_number")
    private String addressNumber;

    //PSČ
    @Size(max = 10, message = "Maximální počet znaků je 10!")
    @Pattern(regexp = "^[0-9]*$", message = "PSČ musí obsahovat pouze čísla!")
    @NotNull
    @Column(name = "zip_code")
    private String zipCode;

    //stát
    @NotNull
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    //list rolí uživatele
    @Nullable
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roleList;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

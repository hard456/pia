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

    @NotNull(message = "Pohlaví musí být vybráno!")
    @Column(name = "sex")
    private String sex;

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

    /**
     * Vrátí id uživatele
     *
     * @return id uživatele
     */
    public Integer getId() {
        return id;
    }

    /**
     * Změní id uživatele
     *
     * @param id id uživatele
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vrátí křestní jméno uživatele
     *
     * @return křestní jméno
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Změní křestní jméno uživatele
     *
     * @param firstname křestní jméno
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Vrátí příjmení uživatele
     *
     * @return příjmení uživatele
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Změní příjmení uživatele
     *
     * @param lastname příjmení uživatele
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Vrátí email uživatele
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Změní email uživatele
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Vrátí přihlašovací login uživatele
     *
     * @return login
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Změní přihlašovací login uživatele
     *
     * @param loginId login
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * Vrátí přihlašovací heslo uživatele
     *
     * @return heslo
     */
    public String getPin() {
        return pin;
    }

    /**
     * Změní heslo uživatele
     *
     * @param pin heslo
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * Vrátí rodné číslo uživatele
     *
     * @return rodné číslo
     */
    public String getPid() {
        return pid;
    }

    /**
     * Změní rodné číslo
     *
     * @param pid rodné číslo
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * Vrátí adresu uživatele
     *
     * @return adresa
     */
    public String getAddress() {
        return address;
    }

    /**
     * Změní adresu uživatele
     *
     * @param address adresa
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Vrátí číslo popisné
     *
     * @return číslo popisné
     */
    public String getAddressNumber() {
        return addressNumber;
    }

    /**
     * Změní číslo popisné
     *
     * @param addressNumber číslo popisné
     */
    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    /**
     * Vrátí PSČ
     *
     * @return PSČ
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Změní PSČ uživatele
     *
     * @param zipCode PSČ
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Vrátí stát uživatele
     *
     * @return stát
     */
    public State getState() {
        return state;
    }

    /**
     * Změní stát uživatele
     *
     * @param state stát
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Vrátí seznam rolí uživatele
     *
     * @return seznam rolí
     */
    public List<Role> getRoleList() {
        return roleList;
    }

    /**
     * Změní seznam rolí uživatele
     *
     * @param roleList seznam rolí
     */
    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    /**
     * Vrátí pohlaví uživatele
     *
     * @return pohlaví
     */
    public String getSex() {
        return sex;
    }

    /**
     * Změní pohlaví uživatele
     *
     * @param sex pohlaví
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
}

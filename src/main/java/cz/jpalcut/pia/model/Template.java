package cz.jpalcut.pia.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "template")
public class Template implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Size(min = 1, max = 50, message = "Název vzoru musí mít 1-50 znaků!")
    @NotNull
    @Column(name = "name")
    private String name;

    @Size(min = 1, max = 17, message = "Číslo účtu musí mít 1-17 znaků!")
    @Pattern(regexp = "[\\d -]+", message = "Číslo účtu obsahuje nepovolené znaky!")
    @NotNull
    @Column(name = "number")
    private String number;

    @Size(min = 4, max = 4, message = "Kód banky musí mít 4 znaky!")
    @Pattern(regexp = "^[0-9]*$", message = "Kód banky musí obsahovat pouze čísla!")
    @Column(name = "code")
    private String code;

    @Nullable
    @Column(name = "value")
    private Double value;

    @Size(max = 10, message = "Maximální počet znaků je 10!")
    @Pattern(regexp = "^[0-9]*$", message = "Variabilní symbol musí obsahovat pouze čísla!")
    @Nullable
    @Column(name = "variable_symbol")
    private String variableSymbol;

    @Size(max = 4, message = "Maximální počet znaků je 4!")
    @Pattern(regexp = "^[0-9]*$", message = "Konstantní symbol musí obsahovat pouze čísla!")
    @Nullable
    @Column(name = "constant_symbol")
    private String constantSymbol;

    @Size(max = 10, message = "Maximální počet znaků je 10!")
    @Pattern(regexp = "^[0-9]*$", message = "Specifický symbol musí obsahovat pouze čísla!")
    @Nullable
    @Column(name = "specific_symbol")
    private String specificSymbol;

    @Nullable
    @Size(max = 100, message = "Maximální počet znaků je 100!")
    @Column(name = "message")
    private String message;

    @Nullable
    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getVariableSymbol() {
        return variableSymbol;
    }

    public void setVariableSymbol(String variableSymbol) {
        this.variableSymbol = variableSymbol;
    }

    public String getConstantSymbol() {
        return constantSymbol;
    }

    public void setConstantSymbol(String constantSymbol) {
        this.constantSymbol = constantSymbol;
    }

    public String getSpecificSymbol() {
        return specificSymbol;
    }

    public void setSpecificSymbol(String specificSymbol) {
        this.specificSymbol = specificSymbol;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

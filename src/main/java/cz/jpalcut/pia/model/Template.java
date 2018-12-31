package cz.jpalcut.pia.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "template")
public class Template implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "number")
    private String number;

    @Column(name = "code")
    private String code;

    @Column(name = "value")
    private Double value;

    @Column(name = "variable_symbol")
    private String variableSymbol;

    @Column(name = "constant_symbol")
    private String constantSymbol;

    @Column(name = "specific_symbol")
    private String specificSymbol;

    @Column(name = "message")
    private String message;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

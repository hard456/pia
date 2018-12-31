package cz.jpalcut.pia.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(schema = "public", name = "transaction")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "income")
    private Boolean income;

    @Column(name = "number")
    private String number;

    @Column(name = "code")
    private String code;

    @Column(name = "value")
    private Double value;

    @Column(name = "maturity")
    private LocalDateTime maturity;

    @Column(name = "variable_symbol")
    private String variableSymbol;

    @Column(name = "constant_symbol")
    private String constantSymbol;

    @Column(name = "specific_symbol")
    private String specificSymbol;

    @Column(name = "message")
    private String message;

    @Column(name = "processing_date")
    private LocalDateTime processingDate;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIncome() {
        return income;
    }

    public void setIncome(Boolean income) {
        this.income = income;
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

    public LocalDateTime getMaturity() {
        return maturity;
    }

    public void setMaturity(LocalDateTime maturity) {
        this.maturity = maturity;
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

    public LocalDateTime getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(LocalDateTime processingDate) {
        this.processingDate = processingDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

package cz.jpalcut.pia.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;


@Entity
@Table(schema = "public", name = "transaction")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "income")
    private Boolean income;

    @Size(min = 1, max = 17, message = "Číslo účtu musí mít 1-17 znaků!")
    @Pattern(regexp = "[\\d -]+", message = "Číslo účtu muže obsahovat čísla nebo pomlčku!")
    @NotNull
    @Column(name = "number")
    private String number;

    @Size(min = 4, max = 4, message = "Kód banky musí mít 4 znaky!")
    @Pattern(regexp = "^[0-9]*$", message = "Kód banky musí obsahovat pouze čísla!")
    @Column(name = "code")
    private String code;

    @NotNull(message = "Částka musí být vyplněna!")
    @Column(name = "value")
    private Double value;

    @NotNull(message = "Datum splatnosti musí být vyplněn!")
    @Column(name = "maturity")
    private Date maturity;

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
    @Column(name = "processing_date")
    private Date processingDate;

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

    public Date getMaturity() {
        return maturity;
    }

    public void setMaturity(Date maturity) {
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

    public Date getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(Date processingDate) {
        this.processingDate = processingDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}

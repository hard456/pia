package cz.jpalcut.pia.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "number")
    private String number;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "blocked_balance")
    private Double blockedBalance;

    @Column(name = "card_number")
    private String cardNumber;

    @Nullable
    @Column(name = "international_payment")
    private Boolean internationalPayment;

    @Nullable
    @Column(name = "limit_payment")
    private Double limitPayment;

    @Nullable
    @Column(name = "card_pin")
    private String cardPin;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getBlockedBalance() {
        return blockedBalance;
    }

    public void setBlockedBalance(Double blockedBalance) {
        this.blockedBalance = blockedBalance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getInternationalPayment() {
        return internationalPayment;
    }

    public void setInternationalPayment(Boolean internationalPayment) {
        this.internationalPayment = internationalPayment;
    }

    public Double getLimitPayment() {
        return limitPayment;
    }

    public void setLimitPayment(Double limitPayment) {
        this.limitPayment = limitPayment;
    }

    public String getCardPin() {
        return cardPin;
    }

    public void setCardPin(String cardPin) {
        this.cardPin = cardPin;
    }
}

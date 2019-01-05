package cz.jpalcut.pia.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Tabulka bankovních účtů
 */
@Entity
@Table(schema = "public", name = "account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //číslo účtu
    @Column(name = "number")
    private String number;

    //zůstatek na účtu
    @Column(name = "balance")
    private Double balance;

    //blokovaná suma nezpracovanými transakcemi
    @Column(name = "blocked_balance")
    private Double blockedBalance;

    @Column(name = "card_number")
    private String cardNumber;

    //povolení nebo zamítnutí mezinárodní platby kartou
    @Column(name = "international_payment")
    private Boolean internationalPayment;

    //limit do kterého může jít uživatel pod 0.00
    @Column(name = "limit_below")
    private Double limitBelow;

    //pin karty
    @Nullable
    @Column(name = "card_pin")
    private String cardPin;

    //vlastník účtu
    @ManyToOne
    @JoinColumn(name = "user_id")
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

    public Double getLimitBelow() {
        return limitBelow;
    }

    public void setLimitBelow(Double limitBelow) {
        this.limitBelow = limitBelow;
    }

    public String getCardPin() {
        return cardPin;
    }

    public void setCardPin(String cardPin) {
        this.cardPin = cardPin;
    }
}

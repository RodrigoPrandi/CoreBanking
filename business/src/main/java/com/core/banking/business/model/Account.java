package com.core.banking.business.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.core.banking.business.model.TransactionType.CREDIT;

@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    private String externalId;

    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transaction> transactions = new ArrayList<>();

    @SuppressWarnings("unused")
    Account() {
    }

    public Account(String externalId) {
        this.externalId = externalId;
    }

    public Long getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void addTransaction(TransactionType transactionType, BigDecimal value) {
        /*if(DEBIT.equals(transactionType) && getBalance().compareTo(value) >= 0 ) {
            throw new InsufficientFound
        }*/
        transactions.add(new Transaction(this, transactionType, value));
    }

    public BigDecimal getBalance() {
        return transactions
                .stream()
                .map(t -> CREDIT.equals(t.getType()) ? t.getValue() : t.getValue().negate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

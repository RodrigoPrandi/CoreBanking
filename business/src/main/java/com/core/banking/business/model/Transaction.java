package com.core.banking.business.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name ="transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Account account;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private BigDecimal value;

    @SuppressWarnings("unused")
    Transaction() {
    }

    Transaction(Account account, TransactionType type, BigDecimal value) {
        this.account = account;
        this.type = type;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getValue() {
        return value;
    }
}

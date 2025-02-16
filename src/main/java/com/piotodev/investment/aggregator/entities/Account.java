package com.piotodev.investment.aggregator.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Account {



    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "account_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    @PrimaryKeyJoinColumn
    private BillingAdress billingAdress;

    @OneToMany(mappedBy = "account")
    private Set<AccountStock> accountStocks;

    public Account(String description, User user, BillingAdress billingAdress, Set<AccountStock> accountStocks) {
        this.description = description;
        this.user = user;
        this.billingAdress = billingAdress;
        this.accountStocks = accountStocks;
    }
}

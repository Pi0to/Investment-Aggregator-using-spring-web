package com.piotodev.investment.aggregator.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piotodev.investment.aggregator.controller.dto.AccountStockOutDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_accounts")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@EqualsAndHashCode
public class Account {


    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id")
    private UUID accountId;

    @Getter
    @Column(name = "account_description")
    private String description;


    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Getter
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

    @JsonIgnore
    public Set<AccountStock> getAccountStocks() {
        return accountStocks;
    }

    @JsonGetter("getAccountStocks")
    public List<AccountStockOutDTO> getAccountStocksDTOs() {
        return accountStocks.stream().map(x -> new AccountStockOutDTO(x.getQuantity(), x.getStock())).toList();
    }

}

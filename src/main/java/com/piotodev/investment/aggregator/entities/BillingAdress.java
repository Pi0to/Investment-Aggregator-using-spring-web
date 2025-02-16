package com.piotodev.investment.aggregator.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_billingadress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillingAdress {

    @Id
    @Column(name = "account_id")
    private UUID id;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    public BillingAdress(Account account, String street, String number) {
        this.account = account;
        this.street = street;
        this.number = number;
    }
}

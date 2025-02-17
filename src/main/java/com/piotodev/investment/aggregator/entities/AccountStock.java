package com.piotodev.investment.aggregator.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_accountstock")
public class AccountStock {

    @EmbeddedId
    private AccountStockId accountStockId;


    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;


    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "quantity")
    private Integer quantity;


}

package com.example.cqrseventsourcing.commonApi.events;

import com.example.cqrseventsourcing.commonApi.enums.AccountStatus;
import lombok.Getter;

public class AccountCreatedEvent extends BaseEvent<String>{

    @Getter
    private double initialeBalance;
    @Getter private String currency;
    @Getter private AccountStatus accountStatus;

    public AccountCreatedEvent(String id, double initialeBalance, String currency, AccountStatus accountStatus) {
        super(id);
        this.initialeBalance = initialeBalance;
        this.currency = currency;
        this.accountStatus = accountStatus;
    }
}

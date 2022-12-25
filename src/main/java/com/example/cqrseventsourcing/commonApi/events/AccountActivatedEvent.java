package com.example.cqrseventsourcing.commonApi.events;

import com.example.cqrseventsourcing.commonApi.enums.AccountStatus;
import lombok.Getter;

public class AccountActivatedEvent extends BaseEvent<String>{


    @Getter private AccountStatus accountStatus;

    public AccountActivatedEvent(String id,  AccountStatus accountStatus) {
        super(id);
        this.accountStatus = accountStatus;
    }
}

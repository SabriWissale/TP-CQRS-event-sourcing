package com.example.cqrseventsourcing.commonApi.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public abstract class BaseCommand<T> {
    @TargetAggregateIdentifier
    @Getter
    T id;

    public BaseCommand(T id) {
        this.id = id;
    }

}

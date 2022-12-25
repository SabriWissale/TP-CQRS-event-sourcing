package com.example.cqrseventsourcing.commonApi.commands;

public class CreditAccountCommand extends BaseCommand<String> {

    private  double amount;
    private  String currency;

    public CreditAccountCommand(String id, double debitAmount, String currency) {
        super(id);
        this.amount = debitAmount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}


package com.ngocnv.springtemplate.tdd;

public class Money {

    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;
        return this.amount == money.amount && currency.equals(money.currency);
    }

    public Money times(int multiplier) {
        return new Money(this.amount * multiplier, currency);
    }

    static Money dolar(int amount) {
        return new Money(amount, "USD");
    }

    static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public String currency() {
        return this.currency;
    }

    @Override
    public String toString() {
        return amount + currency;
    }

    public Money plus(Money append) {
        return new Money(amount + append.amount, currency);
    }
}

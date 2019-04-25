package com.ngocnv.springtemplate.tdd;

import java.util.Objects;

public class Money {

  protected int amount;
  protected String currency;

  public Money(int amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Money money = (Money) o;

    if (amount != money.amount) {
      return false;
    }
    return currency != null ? currency.equals(money.currency) : money.currency == null;
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency);
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

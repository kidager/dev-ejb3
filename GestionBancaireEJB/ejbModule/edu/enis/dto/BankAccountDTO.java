package edu.enis.dto;

import edu.enis.model.ClientEntity;

public class BankAccountDTO {
  private long         accountRib;
  private double       balance;
  private ClientEntity client;

  public long getAccountRib() {
    return accountRib;
  }

  public BankAccountDTO() {}

  public BankAccountDTO(long accountRib, double balance, ClientEntity client) {
    this.accountRib = accountRib;
    this.balance = balance;
    this.client = client;
  }

  public void setAccountRib(long accountRib) {
    this.accountRib = accountRib;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public ClientEntity getClient() {
    return client;
  }

  public void setClient(ClientEntity client) {
    this.client = client;
  }

}

package edu.enis.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BankAccount")
@SuppressWarnings("serial")
public class BankAccountEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long                    accountRib;
  private double                  balance;

  @JoinColumn(name = "clientCin", referencedColumnName = "cin")
  @ManyToOne()
  private ClientEntity            client;

  @OneToMany(mappedBy = "bankAccount")
  private List<TransactionEntity> transactionsList;

  public BankAccountEntity() {}

  public BankAccountEntity(ClientEntity client, double balance) {
    this.client = client;
    this.balance = balance;
    this.client.getAccountsList().add(this);
  }

  public long getAccountRib() {
    return accountRib;
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

  public List<TransactionEntity> getTransactionsList() {
    return transactionsList;
  }

  public void setTransactionsList(List<TransactionEntity> transactionsList) {
    this.transactionsList = transactionsList;
  }
}

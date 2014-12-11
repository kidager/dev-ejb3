package edu.enis.dto;

import java.sql.Timestamp;

import edu.enis.model.BankAccountEntity;

public class TransactionDTO {

  private long              transactionId;
  private BankAccountEntity bankAccount;
  private Timestamp         date;
  private int               type;         // 0 : debit 1 : credit
  private double            amount;

  public TransactionDTO(long transactionId, BankAccountEntity bankAccount, Timestamp date, int type, double amount) {
    this.transactionId = transactionId;
    this.bankAccount = bankAccount;
    this.type = type;
    this.amount = amount;
    this.date = date;
  }

  public long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(long transactionId) {
    this.transactionId = transactionId;
  }

  public BankAccountEntity getBankAccount() {
    return bankAccount;
  }

  public void setBankAccount(BankAccountEntity bankAccount) {
    this.bankAccount = bankAccount;
  }

  public Timestamp getDate() {
    return date;
  }

  public void setDate(Timestamp date) {
    this.date = date;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

}

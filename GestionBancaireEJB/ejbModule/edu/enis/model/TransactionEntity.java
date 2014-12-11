package edu.enis.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Transaction")
@SuppressWarnings("serial")
public class TransactionEntity implements Serializable {

  @Transient
  public static int         TRANSACTION_TYPE_DEBIT  = 0;
  @Transient
  public static int         TRANSACTION_TYPE_CREDIT = 1;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long              transactionId;

  @JoinColumn(name = "accountRib", referencedColumnName = "accountRib")
  @ManyToOne(fetch = FetchType.EAGER)
  private BankAccountEntity bankAccount;

  private Timestamp         date;
  private int               type;                       // 0 : debit 1 : credit
  private double            amount;

  public TransactionEntity() {}

  public TransactionEntity(BankAccountEntity bankAccount, int type, double amount) {
    this.bankAccount = bankAccount;
    this.type = type;
    this.amount = amount;
    this.bankAccount.getTransactionsList().add(this);
  }

  @PrePersist
  @PreUpdate
  public void updateTimestampe() {
    this.date = new Timestamp(new Date().getTime());
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

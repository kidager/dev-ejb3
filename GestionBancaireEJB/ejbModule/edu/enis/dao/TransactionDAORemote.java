package edu.enis.dao;

import javax.ejb.Remote;

import edu.enis.model.TransactionEntity;

@Remote
public interface TransactionDAORemote {
  public long save(TransactionEntity accountTransaction);

  public void delete(TransactionEntity accountTransaction);

  public TransactionEntity find(long transactionPK);
}

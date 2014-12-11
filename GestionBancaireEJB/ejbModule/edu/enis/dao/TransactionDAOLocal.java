package edu.enis.dao;

import javax.ejb.Local;

import edu.enis.model.TransactionEntity;

@Local
public interface TransactionDAOLocal {
  public long save(TransactionEntity accountTransaction);

  public void delete(TransactionEntity accountTransaction);

  public TransactionEntity find(long transactionPK);

}

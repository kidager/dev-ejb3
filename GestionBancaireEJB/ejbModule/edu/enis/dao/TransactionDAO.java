package edu.enis.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.enis.model.TransactionEntity;

@Stateless
public class TransactionDAO implements TransactionDAORemote, TransactionDAOLocal {

  @PersistenceContext
  private EntityManager entityManager;

  public TransactionDAO() {}

  public long save(TransactionEntity accountTransaction) {
    entityManager.persist(entityManager.merge(accountTransaction));
    entityManager.flush();
    return accountTransaction.getTransactionId();
  }

  public void delete(TransactionEntity accountTransaction) {
    accountTransaction = entityManager.merge(accountTransaction);
    accountTransaction.getBankAccount().getTransactionsList().remove(accountTransaction);
    entityManager.remove(accountTransaction);
    entityManager.flush();
  }

  public TransactionEntity find(long transactionPK) {
    return entityManager.find(TransactionEntity.class, transactionPK);
  }

}

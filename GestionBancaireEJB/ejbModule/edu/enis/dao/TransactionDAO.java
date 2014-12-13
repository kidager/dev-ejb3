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
    entityManager.persist(entityManager.contains(accountTransaction) ? accountTransaction : entityManager
        .merge(accountTransaction));
    return accountTransaction.getTransactionId();
  }

  public void delete(TransactionEntity accountTransaction) {
    entityManager.remove(entityManager.contains(accountTransaction) ? accountTransaction : entityManager
        .merge(accountTransaction));
  }

  public TransactionEntity find(long transactionPK) {
    return entityManager.find(TransactionEntity.class, transactionPK);
  }

}

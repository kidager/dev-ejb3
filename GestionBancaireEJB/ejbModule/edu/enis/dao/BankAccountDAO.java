package edu.enis.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.enis.model.BankAccountEntity;

@Stateless
@SuppressWarnings("unchecked")
public class BankAccountDAO implements BankAccountDAORemote, BankAccountDAOLocal {

  @PersistenceContext
  private EntityManager entityManager;

  public BankAccountDAO() {}

  public long save(BankAccountEntity cb) {
    entityManager.persist(entityManager.merge(cb));
    entityManager.flush();
    return cb.getAccountRib();
  }

  public void delete(BankAccountEntity cb) {
    cb = entityManager.merge(cb);
    cb.getClient().getAccountsList().remove(cb);
    entityManager.remove(cb);
    entityManager.flush();
  }

  public BankAccountEntity find(long rib) {
    return entityManager.find(BankAccountEntity.class, rib);
  }

  public List<BankAccountEntity> listAll() {
    Query q = entityManager.createQuery("FROM BankAccountEntity");
    if (q != null) {
      return q.getResultList();
    }
    return null;
  }

  public List<BankAccountEntity> listAllForClient(String cin) {
    Query q = entityManager.createQuery("FROM BankAccountEntity WHERE clientCin = :cin").setParameter("cin", cin);
    if (q != null) {
      return q.getResultList();
    }
    return null;
  }
}

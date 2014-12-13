package edu.enis.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.enis.model.BankAccountEntity;

@Stateless
@SuppressWarnings("unchecked")
public class BankAccountDAO implements BankAccountDAORemote, BankAccountDAOLocal {

  @PersistenceContext
  private EntityManager entityManager;

  public BankAccountDAO() {}

  public long save(BankAccountEntity cb) {
    entityManager.persist(entityManager.contains(cb) ? cb : entityManager.merge(cb));
    return cb.getAccountRib();
  }

  public void delete(BankAccountEntity cb) {
    entityManager.remove(entityManager.contains(cb) ? cb : entityManager.merge(cb));
  }

  public BankAccountEntity find(long rib) {
    return entityManager.find(BankAccountEntity.class, rib);
  }

  public List<BankAccountEntity> listAll() {
    return entityManager.createQuery("FROM BankAccountEntity").getResultList();
  }

  public List<BankAccountEntity> listAllForClient(String cin) {
    List<BankAccountEntity> list = entityManager.createQuery("FROM BankAccountEntity WHERE clientCin = :cin")
        .setParameter("cin", cin)
        .getResultList();
    return list;
  }
}

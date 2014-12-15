package edu.enis.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.enis.model.ClientEntity;

@Stateless
public class ClientDAO implements ClientDAORemote, ClientDAOLocal {

  @PersistenceContext
  private EntityManager entityManager;

  public ClientDAO() {}

  public String save(ClientEntity client) {
    entityManager.persist(entityManager.merge(client));
    entityManager.flush();
    return client.getCin();
  }

  public void delete(ClientEntity client) {
    entityManager.remove(entityManager.merge(client));
    entityManager.flush();
  }

  public ClientEntity find(String cin) {
    return entityManager.find(ClientEntity.class, cin);
  }

  @SuppressWarnings("unchecked")
  public List<ClientEntity> listAll() {
    Query q = entityManager.createQuery("FROM ClientEntity");
    if (q != null) {
      return q.getResultList();
    }
    return null;
  }
}

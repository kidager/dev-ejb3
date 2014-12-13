package edu.enis.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.enis.model.ClientEntity;

@Stateless
public class ClientDAO implements ClientDAORemote, ClientDAOLocal {

  @PersistenceContext
  private EntityManager entityManager;

  public ClientDAO() {}

  public String save(ClientEntity client) {
    entityManager.persist(entityManager.contains(client) ? client : entityManager.merge(client));
    return client.getCin();
  }

  public void delete(ClientEntity client) {
    entityManager.remove(entityManager.contains(client) ? client : entityManager.merge(client));
  }

  public ClientEntity find(String cin) {
    return entityManager.find(ClientEntity.class, cin);
  }

  @SuppressWarnings("unchecked")
  public List<ClientEntity> listAll() {
    return entityManager.createQuery("FROM ClientEntity").getResultList();
  }
}

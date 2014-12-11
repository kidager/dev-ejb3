package edu.enis.dao;

import java.util.List;

import javax.ejb.Local;

import edu.enis.model.ClientEntity;

@Local
public interface ClientDAOLocal {
  public String save(ClientEntity client);

  public void delete(ClientEntity client);

  public ClientEntity find(String cin);

  public List<ClientEntity> listAll();
}

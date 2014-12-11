package edu.enis.dao;

import java.util.List;

import javax.ejb.Remote;

import edu.enis.model.ClientEntity;

@Remote
public interface ClientDAORemote {
  public String save(ClientEntity client);

  public void delete(ClientEntity client);

  public ClientEntity find(String cin);

  public List<ClientEntity> listAll();
}

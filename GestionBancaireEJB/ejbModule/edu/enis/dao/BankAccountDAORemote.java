package edu.enis.dao;

import java.util.List;

import javax.ejb.Remote;

import edu.enis.model.BankAccountEntity;

@Remote
public interface BankAccountDAORemote {

  public long save(BankAccountEntity cb);

  public List<BankAccountEntity> listAll();

  public void delete(BankAccountEntity cb);

  public BankAccountEntity find(long rib);

  public List<BankAccountEntity> listAllForClient(String cin);

}

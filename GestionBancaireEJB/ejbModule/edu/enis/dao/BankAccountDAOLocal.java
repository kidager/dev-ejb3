package edu.enis.dao;

import java.util.List;

import javax.ejb.Local;

import edu.enis.model.BankAccountEntity;

@Local
public interface BankAccountDAOLocal {

  public long save(BankAccountEntity cb);

  public List<BankAccountEntity> listAll();

  public void delete(BankAccountEntity cb);

  public BankAccountEntity find(long rib);

  public List<BankAccountEntity> listAllForClient(String cin);
}

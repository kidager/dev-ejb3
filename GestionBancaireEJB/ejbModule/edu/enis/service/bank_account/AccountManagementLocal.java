package edu.enis.service.bank_account;

import java.util.List;

import javax.ejb.Local;

import edu.enis.model.BankAccountEntity;
import edu.enis.model.ClientEntity;
import edu.enis.model.TransactionEntity;

@Local
public interface AccountManagementLocal {

  // Client actions
  /**
   * @return: Client cin
   */
  public String createNewClient(ClientEntity client);

  public ClientEntity getClientByCin(String cin);

  public List<ClientEntity> getAllClients();

  public String updateClient(ClientEntity client);

  public void deleteClient(String cin);

  // Bank account actions
  /**
   * @return: account RIB
   */
  public long createAccount(BankAccountEntity account);

  public BankAccountEntity getAccountByRib(long rib);

  public List<BankAccountEntity> getAllAccounts(String cin);

  public long updateAccount(BankAccountEntity account);

  public void deleteAccount(BankAccountEntity account);

  // Transactions actions
  public List<TransactionEntity> getAllTransactions(long clientRib);

  // Common account operations
  public boolean debitMoney(BankAccountEntity account, double amount);

  public boolean creditMoney(BankAccountEntity account, double amount);

}

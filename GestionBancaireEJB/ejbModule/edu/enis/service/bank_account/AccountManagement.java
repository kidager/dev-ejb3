package edu.enis.service.bank_account;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.enis.dao.BankAccountDAOLocal;
import edu.enis.dao.ClientDAOLocal;
import edu.enis.dao.TransactionDAOLocal;
import edu.enis.model.BankAccountEntity;
import edu.enis.model.ClientEntity;
import edu.enis.model.TransactionEntity;

@Stateless
public class AccountManagement implements AccountManagementRemote, AccountManagementLocal {

  @EJB
  BankAccountDAOLocal baDAO;

  @EJB
  TransactionDAOLocal transDAO;

  @EJB
  ClientDAOLocal      clientDAO;

  public AccountManagement() {}

  public String createNewClient(ClientEntity client) {
    clientDAO.save(client);
    return client.getCin();
  }

  public void deleteClient(String cin) {
    clientDAO.delete(clientDAO.find(cin));
  }

  public List<ClientEntity> getAllClients() {
    return clientDAO.listAll();
  }

  public ClientEntity getClientByCin(String cin) {
    return clientDAO.find(cin);
  }

  public boolean debitMoney(BankAccountEntity account, double amount) {
    if (account == null || account.getBalance() < amount) { return false; }
    // Debit balance
    account.setBalance(account.getBalance() - amount);
    baDAO.save(account);
    // And then save the transaction
    TransactionEntity trans = new TransactionEntity(account, TransactionEntity.TRANSACTION_TYPE_DEBIT, amount);
    transDAO.save(trans);
    return true;
  }

  public boolean creditMoney(BankAccountEntity account, double amount) {
    if (account == null) { return false; }
    // Credit balance
    account.setBalance(account.getBalance() + amount);
    baDAO.save(account);
    // And then save the transaction
    TransactionEntity trans = new TransactionEntity(account, TransactionEntity.TRANSACTION_TYPE_CREDIT, amount);
    transDAO.save(trans);
    return true;
  }

  public List<TransactionEntity> getAllTransactions(long clientRib) {
    BankAccountEntity ba = baDAO.find(clientRib);
    if (ba == null) return new ArrayList<TransactionEntity>();
    return ba.getTransactionsList();
  }

  @Override
  public List<BankAccountEntity> getAllAccounts(String cin) {
    return baDAO.listAllForClient(cin);
  }

  @Override
  public String updateClient(ClientEntity client) {
    return clientDAO.save(client);
  }

  @Override
  public long createAccount(BankAccountEntity account) {
    baDAO.save(account);
    return account.getAccountRib();
  }

  @Override
  public BankAccountEntity getAccountByRib(long rib) {
    return baDAO.find(rib);
  }

  @Override
  public long updateAccount(BankAccountEntity account) {
    baDAO.save(account);
    return account.getAccountRib();
  }

  @Override
  public void deleteAccount(BankAccountEntity account) {
    baDAO.delete(account);
  }

}

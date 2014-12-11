package edu.enis.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Client")
@SuppressWarnings("serial")
public class ClientEntity implements Serializable {

  @Id
  private String                  cin;

  private String                  firstName;
  private String                  lastName;
  private String                  address;

  @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
  private List<BankAccountEntity> accountsList;

  public ClientEntity() {}

  public ClientEntity(String cin, String firstName, String lastName, String address) {
    this.cin = cin;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
  }

  public String getCin() {
    return cin;
  }

  public void setCin(String cin) {
    this.cin = cin;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<BankAccountEntity> getAccountsList() {
    return accountsList;
  }

  public void setAccountsList(List<BankAccountEntity> accountsList) {
    this.accountsList = accountsList;
  }

}

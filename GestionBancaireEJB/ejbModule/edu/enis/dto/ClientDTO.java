package edu.enis.dto;

public class ClientDTO {

  private String cin;
  private String firstName;
  private String lastName;
  private String address;

  public ClientDTO() {};

  public ClientDTO(String cin, String firstName, String lastName, String address) {
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

}

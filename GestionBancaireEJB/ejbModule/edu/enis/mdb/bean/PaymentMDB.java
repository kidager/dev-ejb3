package edu.enis.mdb.bean;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.ejb3.annotation.Depends;

import edu.enis.model.BankAccountEntity;
import edu.enis.service.bank_account.AccountManagementLocal;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/payementBancaire")
})
@Depends("jboss.mq.destination:service=Queue,name=paymentMDB")
public class PaymentMDB implements MessageListener {

  @EJB
  AccountManagementLocal baManager;

  public void onMessage(Message recvMsg) {
    String msg = "";
    try {
      msg = ((javax.jms.TextMessage) recvMsg).getText();
    } catch (javax.jms.JMSException e) {
      System.out.println("Error receiving message");
      e.printStackTrace();
      return;
    }

    String[] data = msg.split(":");
    if (data[0] == null || data[1] == null) {
      System.out.println("False data received : " + msg);
      return;
    }

    try {
      long rib = Long.parseLong(data[0]);
      double amount = Double.parseDouble(data[1]);
      BankAccountEntity ba = baManager.getAccountByRib(rib);
      baManager.debitMoney(ba, amount);
      System.out.println("Debited " + amount + " from " + rib);
    } catch (Exception e) {
      System.out.println("ERROR, msg = " + msg);
    }

  }

}

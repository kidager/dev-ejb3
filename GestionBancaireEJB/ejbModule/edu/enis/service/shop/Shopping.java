package edu.enis.service.shop;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import edu.enis.service.calc.CalculatriceRemote;

/**
 * Session Bean implementation class Shopping
 */
@Stateful
public class Shopping implements ShoppingRemote, ShoppingLocal {

  private static final String QUEUE_NAME   = "queue/payementBancaire";

  @EJB
  private CalculatriceRemote  calculatrice;

  List<String>                listProduit  = null;
  List<Integer>               listQuantite = null;
  List<Double>                listPrix     = null;
  double                      total        = 0;

  public Shopping() {
    listProduit = new ArrayList<String>();
    listQuantite = new ArrayList<Integer>();
    listPrix = new ArrayList<Double>();
    total = 0;
  }

  public void achatProduit(String produit, int quantite, double prix) {
    // ajout au caddie
    int index = listProduit.indexOf(produit);
    if (index != -1) {
      listQuantite.set(index, listQuantite.get(index) + quantite);
    } else {
      listProduit.add(produit);
      listQuantite.add(quantite);
      listPrix.add(prix);
    }
  }

  public String listProduitAchete() {
    // retourne la liste des produits achetés
    String str = "";
    for (String s : listProduit) {
      str += s + " |" + // Produit
          "x" + listQuantite.get(listProduit.indexOf(s)) + " | " + // Quantité
          listPrix.get(listProduit.indexOf(s)) + "TND\n"; // Prix
    }
    return str;
  }

  public boolean suppProduit(String produit, int quantite) {
    int index = listProduit.indexOf(produit);
    if (index != -1) {
      if (quantite >= listQuantite.get(index)) {
        listProduit.remove(index);
        listQuantite.remove(index);
        listPrix.remove(index);
      } else {
        listQuantite.set(index, listQuantite.get(index) - quantite);
      }
      return true;
    } else {
      return false;
    }
  }

  public double getTotal() {
    // retourne le prix des produits dans le caddie
    // utiliser les méthodes offertes par l’EJB injecté Calculatrice
    double total = 0;
    int index = 0;
    for (String s : listProduit) {
      index = listProduit.indexOf(s);
      total = calculatrice.som(total, calculatrice.mult(listPrix.get(index), listQuantite.get(index)));
    }
    return total;
  }

  public void payement(long rib) {
    // affiche un message de réussite du payement du total
    System.out.println("Payment from [RIB:" + rib + "|Total:" + getTotal() + "] is being sent!");

    QueueConnection cnn = null;
    QueueSender sender = null;
    QueueSession session = null;
    try {
      InitialContext ctx = new InitialContext();
      Queue queue = (Queue) ctx.lookup(QUEUE_NAME);
      QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
      cnn = factory.createQueueConnection();
      session = cnn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

      TextMessage msg = session.createTextMessage(Long.toString(rib) + ":" + Double.toString(getTotal()));
      sender = session.createSender(queue);
      sender.send(msg);
      System.out.println("Payment sent succefully to " + QUEUE_NAME);
    } catch (JMSException | NamingException ex) {
      ex.printStackTrace();
      return;
    } finally {
      if (cnn != null) {
        try {
          cnn.close();
        } catch (JMSException ex) {
          ex.printStackTrace();
          return;
        }
      }
    }
  }

  @javax.annotation.PostConstruct
  public void showMessagePostConstruct() {
    // une méthode qui affiche un message pour
    // confirmer la création d’une nouvelle instance (nouveau client) du bean
    System.out.println("Shopping instance is created");
  }

}

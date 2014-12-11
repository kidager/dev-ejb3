package test;

import javax.naming.InitialContext;

import edu.enis.service.shop.ShoppingRemote;

public class TestEJB {

  public static void main(String[] args) {

    ShoppingRemote shoppingCart;

    try {
      InitialContext ctx = new InitialContext();
      shoppingCart = (ShoppingRemote) ctx.lookup("GestionBancaireEnterprise/Shopping/remote");
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    shoppingCart.achatProduit("Computer", 1, 1200);
    shoppingCart.achatProduit("Mouse", 2, 10);
    shoppingCart.achatProduit("Milk", 1, 1.060);
    shoppingCart.achatProduit("Bread", 4, 0.230);

    System.out.println("Your product list :\n" + shoppingCart.listProduitAchete());
    System.out.println("Your total is :" + shoppingCart.getTotal());

    System.out.println("Deleting on milk pack");
    shoppingCart.suppProduit("Milk", 1);

    System.out.println("Your product list :\n" + shoppingCart.listProduitAchete());
    System.out.println("Your total is :" + shoppingCart.getTotal());

    // 9 is the RIB
    shoppingCart.payement(9);

  }
}

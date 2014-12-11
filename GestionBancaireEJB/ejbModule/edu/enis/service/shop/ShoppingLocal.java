package edu.enis.service.shop;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ShoppingLocal {

  List<String>  listProduit  = null;
  List<Integer> listQuantite = null;
  List<Double>  listPrix     = null;
  double        total        = 0;

  public void achatProduit(String produit, int quantite, double prix);

  public String listProduitAchete();

  public boolean suppProduit(String produit, int quantite);

  public double getTotal();

  public void payement(long rib);

}

package edu.enis.service.shop;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ShoppingRemote {

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

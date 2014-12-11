package edu.enis.service.calc;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class Calculatrice
 */
@Stateless
public class Calculatrice implements CalculatriceRemote, CalculatriceLocal {

  /**
   * Default constructor.
   */
  public Calculatrice() {}

  public double som(double x, double y) {
    return x + y;
  }

  public double mult(double x, double y) {
    return x * y;
  }

  public double sous(double x, double y) {
    return x - y;
  }

}

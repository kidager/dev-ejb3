package edu.enis.service.calc;

import javax.ejb.Remote;

@Remote
public interface CalculatriceRemote {
  public double som(double x, double y);

  public double mult(double x, double y);

  public double sous(double x, double y);
}

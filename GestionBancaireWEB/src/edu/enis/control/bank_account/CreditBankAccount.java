package edu.enis.control.bank_account;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.enis.model.BankAccountEntity;
import edu.enis.service.bank_account.AccountManagementLocal;

@SuppressWarnings("serial")
public class CreditBankAccount extends HttpServlet {

  @EJB
  AccountManagementLocal baManager;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String sRib = "";
    String sAmount = "";

    double amount = 0;
    long rib = 0;

    response.setContentType("application/json");

    try {
      sRib = request.getParameter("rib");
      sAmount = request.getParameter("amount");
      if (sRib == null || sAmount == null)
        throw new NullPointerException();
    } catch (NullPointerException ex) {
      response.getWriter().println("{status:0,msg:\"Missing parameters\"}");
      return;
    }

    try {
      amount = Double.parseDouble(sAmount);
      rib = Long.parseLong(sRib);
    } catch (NumberFormatException ex) {
      response.getWriter().println("{status:0,msg:\"Invalid amount or RIB\"}");
      return;
    }

    BankAccountEntity ba = baManager.getAccountByRib(rib);
    baManager.creditMoney(ba, amount);

    response.getWriter().println("{"
        + "\"status\":1,"
        + "\"msg\":\"Credited account " + rib + " with " + amount + " TND\","
        + "\"balance\":" + baManager.getAccountByRib(rib).getBalance()
        + "}");
  }
}

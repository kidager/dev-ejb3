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
public class DeleteBankAccount extends HttpServlet {

  @EJB
  AccountManagementLocal baManager;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    long rib = 0;
    String cin = "";
    try {
      rib = Long.parseLong(request.getParameter("rib"));
      BankAccountEntity ba = baManager.getAccountByRib(rib);

      cin = ba.getClient().getCin();
      baManager.deleteAccount(ba);

      response.sendRedirect("ShowClient?cin=" + cin);
    } catch (NullPointerException e) {
      e.printStackTrace();
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}

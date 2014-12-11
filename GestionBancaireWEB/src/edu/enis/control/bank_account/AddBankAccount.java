package edu.enis.control.bank_account;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.enis.model.BankAccountEntity;
import edu.enis.model.ClientEntity;
import edu.enis.service.bank_account.AccountManagementLocal;

@SuppressWarnings("serial")
public class AddBankAccount extends HttpServlet {

  @EJB
  AccountManagementLocal baManager;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String cin = request.getParameter("cin");
    try {
      if (cin == null || cin.trim() == "") {
        request.getRequestDispatcher("/addBankAccount.jsp").forward(request, response);
        return;
      }

      ClientEntity client = baManager.getClientByCin(cin);
      if (client == null) throw new Exception();

      BankAccountEntity account = new BankAccountEntity(client, 0);
      baManager.createAccount(account);
    } catch (Exception e) {
      request.setAttribute("msg", "The client is not found!");
      e.printStackTrace();
    }
    response.sendRedirect("ShowClient?cin=" + cin);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}

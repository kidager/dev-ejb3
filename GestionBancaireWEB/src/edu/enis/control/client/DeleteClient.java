package edu.enis.control.client;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.enis.service.bank_account.AccountManagementLocal;

@SuppressWarnings("serial")
public class DeleteClient extends HttpServlet {

  @EJB
  AccountManagementLocal baManager;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String cin = null;
    try {
      cin = request.getParameter("cin");

      baManager.deleteClient(cin);
    } catch (NullPointerException | NumberFormatException e) {
      e.printStackTrace();
    }

    response.sendRedirect("ListAllAccounts");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}

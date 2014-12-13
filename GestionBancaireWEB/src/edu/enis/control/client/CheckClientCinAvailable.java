package edu.enis.control.client;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.enis.model.ClientEntity;
import edu.enis.service.bank_account.AccountManagementLocal;

@SuppressWarnings("serial")
public class CheckClientCinAvailable extends HttpServlet {

  @EJB
  AccountManagementLocal baManager;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String cin = request.getParameter("cin");

    response.setContentType("application/json");

    if (cin == null) {
      response.getWriter().println("{\"status\":0,\"msg\":\"No cin number to check.\"}");
      return;
    }

    ClientEntity client = baManager.getClientByCin(cin);

    if (client != null) {
      response.getWriter().println(
          "{\"status\":0,"
              + "\"msg\":\"cin " + cin + " already exists for client " + client.getFirstName() + " "
              + client.getLastName() + ".\"}");
      return;
    }

    response.getWriter().println("{\"status\":1,\"msg\":\"cin " + cin + " does not exist.\"}");
  }

}

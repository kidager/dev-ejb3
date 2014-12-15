package edu.enis.control.client;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.enis.common.helper.ClientHelper;
import edu.enis.dto.ClientDTO;
import edu.enis.exception.InvalidNameException;
import edu.enis.model.ClientEntity;
import edu.enis.service.bank_account.AccountManagementLocal;

@SuppressWarnings("serial")
public class EditClient extends HttpServlet {

  @EJB
  AccountManagementLocal baManager;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String cin = "";
    String firstName = "";
    String lastName = "";
    String address = "";

    try {
      cin = request.getParameter("firstName");
      firstName = request.getParameter("firstName");
      lastName = request.getParameter("lastName");
      address = request.getParameter("address");

      if (firstName.trim().length() < 3)
        throw new InvalidNameException(firstName);
      if (lastName.trim().length() < 3)
        throw new InvalidNameException(lastName);
      if (address.trim().length() < 3)
        throw new InvalidNameException(address);

    } catch (NullPointerException e) {
      request.setAttribute("error", "All the parameters must be set!");
    } catch (InvalidNameException e) {
      request.setAttribute("error", e.getMessage());
    }

    try {
      ClientEntity client = baManager.getClientByCin(cin);
      client.setFirstName(firstName);
      client.setLastName(lastName);
      client.setAddress(address);

      request.setAttribute("msg", "Client [CIN:" + cin + "] edited successfully : " + firstName +
          " " + lastName);
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "Error!");
    }

    if (request.getAttribute("error") != null) {
      request.getRequestDispatcher("/editClient.jsp").forward(request, response);
    } else {
      response.sendRedirect("/ShowClient?cin=" + cin);
    }

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String cin = request.getParameter("cin");
    ClientDTO client = ClientHelper.modelToDTO(baManager.getClientByCin(cin));
    request.setAttribute("client", client);
    request.getRequestDispatcher("/editClient.jsp").forward(request, response);
  }

}

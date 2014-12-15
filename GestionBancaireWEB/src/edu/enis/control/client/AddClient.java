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
import edu.enis.service.bank_account.AccountManagementLocal;

@SuppressWarnings("serial")
public class AddClient extends HttpServlet {

  @EJB
  AccountManagementLocal baManager;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String cin = "";
    String firstName = "";
    String lastName = "";
    String address = "";

    try {
      cin = request.getParameter("cin");
      firstName = request.getParameter("firstName");
      lastName = request.getParameter("lastName");
      address = request.getParameter("address");
      if (cin.trim().length() != 8)
        throw new InvalidNameException(cin);
      if (firstName.trim().length() < 3)
        throw new InvalidNameException(firstName);
      if (lastName.trim().length() < 3)
        throw new InvalidNameException(lastName);
      if (address.trim().length() < 3)
        throw new InvalidNameException(address);
    } catch (NullPointerException e) {
      request.setAttribute("error", "The two parameters must be set!");
    } catch (InvalidNameException e) {
      request.setAttribute("error", e.getMessage());
    }

    ClientDTO cDTO = new ClientDTO(cin, firstName, lastName, address);
    try {
      baManager.createNewClient(ClientHelper.dtoToModel(cDTO));
      request.setAttribute("msg", "Client added successfully : " + cDTO.getFirstName() +
          " " + cDTO.getLastName() + " [CIN:" + cDTO.getCin() + "]");
    } catch (Exception e) {
      request.setAttribute("error", "The CIN " + cDTO.getCin() + " already exists!");
    }

    if (request.getAttribute("error") != null) {
      request.getRequestDispatcher("/addClient.jsp").forward(request, response);
    } else {
      response.sendRedirect("ListAllAccounts");
    }

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/addClient.jsp").forward(request, response);
  }

}

package edu.enis.control.client;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.enis.common.helper.ClientHelper;
import edu.enis.dto.ClientDTO;
import edu.enis.service.bank_account.AccountManagementLocal;

@SuppressWarnings("serial")
public class ListAllAccounts extends HttpServlet {
  @EJB
  AccountManagementLocal baManager;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<ClientDTO> allClients = ClientHelper.modelListToDTOList(baManager.getAllClients());

    request.setAttribute("allClients", allClients);
    request.getRequestDispatcher("/index.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }

}

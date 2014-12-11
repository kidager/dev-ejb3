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
import edu.enis.model.BankAccountEntity;
import edu.enis.service.bank_account.AccountManagementLocal;

@SuppressWarnings("serial")
public class ShowClient extends HttpServlet {

  @EJB
  AccountManagementLocal baManager;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String cin = request.getParameter("cin");
    ClientDTO client = ClientHelper.modelToDTO(baManager.getClientByCin(cin));
    List<BankAccountEntity> accountList = baManager.getAllAccounts(client.getCin());

    request.setAttribute("client", client);
    request.setAttribute("accountList", accountList);
    request.getRequestDispatcher("/client.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}

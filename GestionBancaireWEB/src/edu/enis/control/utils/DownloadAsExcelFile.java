package edu.enis.control.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.enis.model.ClientEntity;
import edu.enis.service.bank_account.AccountManagementLocal;

@SuppressWarnings("serial")
public class DownloadAsExcelFile extends HttpServlet {
  @EJB
  AccountManagementLocal baManager;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<ClientEntity> allAccounts = baManager.getAllClients();

    SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy_HHmmss");
    String filename = "report_" + fmt.format(new Date()) + ".csv";
    response.setHeader("Content-disposition", "attachment; filename=" + filename);
    response.setContentType("application/vnd.ms-excel");

    response.getWriter().print("CIN,First Name,Last Name, Address\n");
    for (ClientEntity client : allAccounts) {
      response.getWriter().println(client.getCin() + "," + client.getFirstName() + "," + client.getLastName()
          + "," + client.getAddress());
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}

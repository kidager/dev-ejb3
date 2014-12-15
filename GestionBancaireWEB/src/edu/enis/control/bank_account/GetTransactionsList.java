package edu.enis.control.bank_account;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.enis.model.TransactionEntity;
import edu.enis.service.bank_account.AccountManagementLocal;

@SuppressWarnings("serial")
public class GetTransactionsList extends HttpServlet {

  @EJB
  AccountManagementLocal baManager;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String sRib = request.getParameter("rib");

    response.setContentType("application/json");

    if (sRib == null) {
      response.getWriter().println("{\"status\":0, \"msg\":\"RIB parameter is not set\"}");
      return;
    }

    long rib = 0;
    try {
      rib = Long.parseLong(sRib);
    } catch (NumberFormatException ex) {
      ex.printStackTrace();
      response.getWriter().println("{\"status\":0, \"msg\":\"RIB is not a valid long\"}");
      return;
    }

    Set<TransactionEntity> listTrans = new HashSet<TransactionEntity>();
    try {
      listTrans = baManager.getAllTransactions(rib);
    } catch (Exception ex) {
      ex.printStackTrace();
      response.getWriter().println("{\"status\":0, \"msg\":\"EJB Error\"}");
      return;
    }

    String message = "";
    message += "<table>";
    message += "<tr style='background-color:#87BAF5'>";
    message += "<td style='text-align:center'><strong>DATE</strong></td>";
    message += "<td style='text-align:center'><strong>TYPE</strong></td>";
    message += "<td style='text-align:center'><strong>AMOUNT</strong></td>";
    message += "</tr>";

    if (listTrans.isEmpty()) {
      message += "<tr>";
      message += "<td colspan='3' style='text-align:center'>No transactions yet!</td>";
      message += "</tr>";
    } else {
      Iterator<TransactionEntity> iter;

      Comparator<TransactionEntity> comp = new Comparator<TransactionEntity>() {
        public int compare(TransactionEntity o1, TransactionEntity o2) {
          return Long.compare(o1.getDate().getTime(), o2.getDate().getTime());
        }
      };

      Set<TransactionEntity> list = new TreeSet<TransactionEntity>(comp);
      list.addAll(listTrans);
      iter = list.iterator();

      while (iter.hasNext()) {
        TransactionEntity trans = iter.next();
        message += "<tr>";
        message += "<td>" + formatDate(trans.getDate()) + "</td>";
        message += "<td><strong>"
            + (trans.getType() == TransactionEntity.TRANSACTION_TYPE_CREDIT
                ? "<font color='#0F0'>Credit</font>"
                : "<font color='#F00'>Debit</font>")
            + "</strong></td>";
        message += "<td>" + trans.getAmount() + " TND</td>";
        message += "</tr>";
      }
    }

    message += "</table>";

    response.getWriter().println("{\"status\":1, \"msg\":\"" + message.replace("\"", "\\\"") + "\"}");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  private String formatDate(Timestamp time) {
    return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(time.getTime()));
  }

}

package kr.co.mz.tutorial.jsp;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import kr.co.mz.tutorial.jsp.db.HikariCPListener;
import kr.co.mz.tutorial.jsp.db.QueryManager;


public class TutorialServletContainer extends HttpServlet {

  public static final String JSP_PATH = "/WEB-INF/views/home.jsp";

  private HikariCPListener hikariCPListener;

//  @Override
//  public void init(ServletConfig config) throws ServletException {
//    super.init(config);
//
//    hikariCPListener = new HikariCPListener();
//    hikariCPListener.contextInitialized(new ServletContextEvent(getServletContext()));
//
//  }

  @Override
//  protected void doGet(HttpServletRequest request, HttpServletResponse response)
//      throws ServletException, IOException {
//    var dataSource = (HikariDataSource) getServletContext().getAttribute("dataSource");
//    try (
//        var conn = dataSource.getConnection();
//    ) {
//
//      RequestDispatcher rd = request.getRequestDispatcher(JSP_PATH);
//      var pst = conn.prepareStatement(QueryManager.getQuery("SELECT_ALL_DEPARTMENT"));
//      var rs = pst.executeQuery();
//      String rss = "";
//      if (rs.next()) {
//        rss = rs.getString("department_name");
//      }
//      request.setAttribute("rss", rss);
//      rd.forward(request, response);
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//  }
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    var dataSource = (HikariDataSource) getServletContext().getAttribute("dataSource");
    try (
        var conn = dataSource.getConnection();
    ) {

      var pst = conn.prepareStatement(QueryManager.getQuery("SELECT_ALL_DEPARTMENT"));
      var rs = pst.executeQuery();
      String rss = "";
      if (rs.next()) {
        rss = rs.getString("department_name");
      }

      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<html><body>");
      out.println("<h1>Department Name: " + rss + "</h1>");
      out.println("</body></html>");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


}


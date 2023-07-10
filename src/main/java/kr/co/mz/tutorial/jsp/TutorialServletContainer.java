package kr.co.mz.tutorial.jsp;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import kr.co.mz.tutorial.jsp.dao.DepartmentDao;
import kr.co.mz.tutorial.jsp.db.HikariCPListener;
import kr.co.mz.tutorial.jsp.dto.DepartmentDto;


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
    var departmentDao = new DepartmentDao(dataSource);
    List<DepartmentDto> list = null;
    try {
      list = departmentDao.findAll();
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<html><body>");
      for (DepartmentDto d : list) {
        out.println("<h1>Department Name: " + d + "</h1>");
      }
      out.println("</body></html>");


    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }


}


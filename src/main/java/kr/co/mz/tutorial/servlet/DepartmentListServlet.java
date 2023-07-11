package kr.co.mz.tutorial.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import kr.co.mz.tutorial.dao.DepartmentDao;
import kr.co.mz.tutorial.db.QueryManager;
import kr.co.mz.tutorial.dto.DepartmentDto;


public class DepartmentListServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    List<DepartmentDto> list;
    var dao = new DepartmentDao();
    dao.setDataSource((DataSource) getServletContext().getAttribute("dataSource"));
    dao.setQueryManager((QueryManager) getServletContext().getAttribute("queryManager"));
    try {
      list = dao.findAll();
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<html><body>");
      for (DepartmentDto d : list) {
        out.println("<p>Department Name: " + d + "</p>");
      }
      out.println("</body></html>");


    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }


}


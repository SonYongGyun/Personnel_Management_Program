package kr.co.mz.tutorial.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import kr.co.mz.tutorial.dao.DepartmentDao;
import kr.co.mz.tutorial.db.QueryManager;

public class FindOneDepartment extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    var out = response.getWriter();

    var key = request.getParameter("nameIs");
    String name = "";
    if (key == null || key.equals("")) {
      out.println("<script>alert('이름을 정확히 입력해주세요');</script>");
      response.sendRedirect("/home");
      System.out.println("아니 여ㄱ기왜안와.");
      return;
    }

    name = key;

    var departmentDao = new DepartmentDao();
    departmentDao.setDataSource((DataSource) getServletContext().getAttribute("dataSource"));
    departmentDao.setQueryManager((QueryManager) getServletContext().getAttribute("queryManager"));
    try {
      var dto = departmentDao.findOneByDepartmentName(name);
      out.println("<html><body>");
      out.println("<p>Department Name: " + dto.get() + "</p>");
      out.println("</body></html>");

    } catch (SQLException e) {
      e.printStackTrace();
      response.sendRedirect("/home");

    }

  }
}

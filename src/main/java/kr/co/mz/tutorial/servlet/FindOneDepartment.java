package kr.co.mz.tutorial.servlet;

import static kr.co.mz.tutorial.Constants.DATASOURCE_CONTEXT_KEY;
import static kr.co.mz.tutorial.Constants.QUERYMANAGER_CONTEXT_KEY;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;
import kr.co.mz.tutorial.DataBaseException;
import kr.co.mz.tutorial.dao.DepartmentDao;
import kr.co.mz.tutorial.db.QueryManager;
import kr.co.mz.tutorial.dto.DepartmentDto;

public class FindOneDepartment extends HttpServlet {

  private DataSource dataSource;
  private QueryManager queryManager;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    dataSource = (DataSource) getServletContext().getAttribute(DATASOURCE_CONTEXT_KEY);
    queryManager = (QueryManager) getServletContext().getAttribute(QUERYMANAGER_CONTEXT_KEY);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    var out = response.getWriter();

    var key = request.getParameter("filteredKey");
    System.out.println(key);
    var findHow = request.getParameter("find");

//    try {
    Optional<DepartmentDto> dto = findOneByKey(key, findHow);

    out.println("<html><body>");
    if (dto.isEmpty()) {
      out.println("<p>Department Name: " + "No Result" + "</p>");
    } else {
      out.println("<p>Department Name: " + dto.get() + "</p>");
    }

    out.println("<button onclick=\"location.href='/home';\">Go Home</button>");
    out.println("</body></html>");

//    } catch (SQLException e) {
//      이 작업을 이제 exception 객체가 대신 해주는것.
//      System.out.println("No Object");
//      e.printStackTrace();
//      response.sendRedirect("/home");
//    }
  }

  private Optional<DepartmentDto> findOneByKey(String filteredKey, String howToFind) {
    try (
        var conn = dataSource.getConnection();
    ) {
      var departmentDao = new DepartmentDao(conn, queryManager);
      Optional<DepartmentDto> optionalDepartmentDto = Optional.empty();

      if (howToFind.equals("byName")) {
        optionalDepartmentDto = departmentDao.findOneByDepartmentName(filteredKey);
      } else if (howToFind.equals("bySeq")) {
        optionalDepartmentDto = departmentDao.findOneBySeq(Long.parseLong(filteredKey));
      }

      return optionalDepartmentDto;

    } catch (SQLException sqle) {
      throw new DataBaseException("데이터베이스 관련 문제가 발생했습니다: " + sqle.getMessage(), sqle);
    }
  }

}

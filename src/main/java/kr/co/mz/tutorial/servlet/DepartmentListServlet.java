package kr.co.mz.tutorial.servlet;

import static kr.co.mz.tutorial.Constants.DATASOURCE_CONTEXT_KEY;
import static kr.co.mz.tutorial.Constants.QUERYMANAGER_CONTEXT_KEY;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import kr.co.mz.tutorial.DataBaseException;
import kr.co.mz.tutorial.dao.DepartmentDao;
import kr.co.mz.tutorial.db.QueryManager;
import kr.co.mz.tutorial.dto.DepartmentDto;
import kr.co.mz.tutorial.dto.EmployeeDto;
import kr.co.mz.tutorial.dto.ProjectDto;


public class DepartmentListServlet extends HttpServlet {

  private DataSource dataSource;
  private QueryManager queryManager;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    dataSource = (DataSource) getServletContext().getAttribute(DATASOURCE_CONTEXT_KEY);
    queryManager = (QueryManager) getServletContext().getAttribute(QUERYMANAGER_CONTEXT_KEY);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    List<DepartmentDto> list;
    try (
        PrintWriter out = response.getWriter();
    ) {
      list = findAll();
      out.println("<html><body>");
      for (DepartmentDto d : list) {
        out.println("<p>Department Name: " + d.toHalfString() + "</p>");

        for (ProjectDto project : d.getProjectsSet()) {
          out.println(
              "<button onclick=\"location.href='/findOneProject?key=" + project.getSeq()
                  + "&find=bySeq';\"> See " + project.getProjectName() + " Details </button>");
        }

        for (EmployeeDto employee : d.getEmployeesSet()) {
          out.println(
              "<button onclick=\"location.href='/findOneEmployee?key=" + employee.getSeq()
                  + "&find=bySeq';\"> See " + employee.getEmployeeName() + " Details </button>");
        }
      }
      out.println("</body></html>");
    }

  }

  private List<DepartmentDto> findAll() {
    try (
        var conn = dataSource.getConnection();
    ) {
      var dao = new DepartmentDao(conn, queryManager);
      return dao.findAll();
    } catch (SQLException sqle) {
      throw new DataBaseException("데이터베이스 커넥션 오류가 났습니다: " + sqle.getMessage(), sqle);
    }
  }

//  @Override  listener 가 대신 해준다.
//  public void destroy() {
//    super.destroy();
//    if (dataSource != null) {
//      var hikariDataSource = (HikariDataSource) dataSource;
//      hikariDataSource.close();
//    }
//  }
}


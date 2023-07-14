package kr.co.mz.tutorial.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import kr.co.mz.tutorial.dto.DepartmentDto;
import kr.co.mz.tutorial.dto.EmployeeDto;
import kr.co.mz.tutorial.dto.ProjectDto;
import kr.co.mz.tutorial.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DepartmentListServlet extends HttpServlet {

  private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentListServlet.class);

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    LOGGER.debug("DepartmentListServlet Class is registered.");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");

    List<DepartmentDto> list = new DepartmentService(getServletContext()).findAll();
    try (
        PrintWriter out = response.getWriter();
    ) {
      out.println("<html><body>");
      for (DepartmentDto d : list) {
        out.println("<h1>Department Name: " + d.getDepartmentName() + "</h1>");
        out.println("<p>Department Name: " + d.toStringWithOutSet() + "</p>");

        out.println("<p>Managing Projects</p>");
        for (ProjectDto project : d.getProjectsSet()) {
          out.println(
              "<button onclick=\"location.href='/find-one/project?key=" + d.getSeq()
                  + "&find=bySeq';\"> See " + project.getProjectName() + " Details </button>");
        }
        out.println("<br >");

        out.println("<p>Employees</p>");
        for (EmployeeDto employee : d.getEmployeesSet()) {
          out.println(
              "<button onclick=\"location.href='/find-one/employee?key=" + d.getSeq() + "&find=bySeq';\"> See "
                  + employee.getEmployeeName() + " Details </button>");
        }
      }
      out.println("</body></html>");
    }
  }

//  private List<DepartmentDto> findAll() {
//    try (
//        var conn = hikariDataSource.getConnection();
//    ) {
//      var dao = new DepartmentDao(conn, queryManager);
//      return dao.findAll();
//    } catch (SQLException sqle) {
//      throw new DataBaseException("데이터베이스 커넥션 오류가 났습니다: " + sqle.getMessage(), sqle);
//    }
//  }

//  @Override  listener 가 대신 해준다.
//  public void destroy() {
//    super.destroy();
//    if (hikariDataSource != null) {
//      var hikariDataSource = (HikariDataSource) hikariDataSource;
//      hikariDataSource.close();
//    }
//  }
}


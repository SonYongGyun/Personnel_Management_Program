package kr.co.mz.tutorial.servlet;

import static kr.co.mz.tutorial.Constants.FILTERED_PARAMETER_KEY;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import kr.co.mz.tutorial.dto.DepartmentDto;
import kr.co.mz.tutorial.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindOneDepartmentServlet extends HttpServlet {

  private static final Logger LOGGER = LoggerFactory.getLogger(FindOneDepartmentServlet.class);
//  private HikariDataSource hikariDataSource;
//  private QueryManager queryManager;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
//    hikariDataSource = (HikariDataSource) getServletContext().getAttribute(HIKARI_DATASOURCE_CONTEXT_KEY);
//    queryManager = (QueryManager) getServletContext().getAttribute(QUERY_MANAGER_CONTEXT_KEY);
    LOGGER.debug("DepartmentListServlet Class is registered.");

  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    var out = response.getWriter();

    var key = (String) request.getAttribute(FILTERED_PARAMETER_KEY);
    var findHow = request.getParameter("find");

//    try {
    Optional<DepartmentDto> dto = new DepartmentService(getServletContext()).findOneByKey(key, findHow);
    DepartmentDto foundDto = new DepartmentDto();
    out.println("<html><body>");
    if (dto.isEmpty()) {
      out.println("<p>Department Name: " + "No Result" + "</p>");
    } else {
      foundDto = dto.get();
      out.println("<h1>Department Name: " + foundDto.getDepartmentName() + "</h1>");
    }
    out.println("<p> Seq: " + foundDto.getSeq() + "</p>");
    out.println("<p> Location: " + foundDto.getLocation() + "</p>");
    out.println("<p> TotalProjects: " + foundDto.getTotalProjects() + "</p>");
    out.println("<p> TotalEmployees: " + foundDto.getTotalEmployees() + "</p>");
    out.println("<p> CreatedBy: " + foundDto.getCreatedBy() + "</p>");
    out.println("<p> CreatedDate: " + foundDto.getCreatedTime() + "</p>");
    out.println("<p> ModifiedBy: " + foundDto.getModifiedBy() + "</p>");
    out.println("<p> ModifiedTime: " + foundDto.getModifiedTime() + "</p>");

    out.println("<button onclick=\"location.href='/home';\">Go Home</button>");
    out.println("</body></html>");
    out.close();
//    } catch (SQLException e) {
//      이 작업을 이제 exception 객체가 대신 해주는것.
//      System.out.println("No Object");
//      e.printStackTrace();
//      response.sendRedirect("/home");
//    }
  }

//  private Optional<DepartmentDto> findOneByKey(String filteredKey, String howToFind) {
//    try (
//        var conn = hikariDataSource.getConnection();
//    ) {
//      var departmentDao = new DepartmentDao(conn, queryManager);
//
//      if (howToFind.equals("byName")) {
//        return departmentDao.findOneByDepartmentName(filteredKey);
//      } else if (howToFind.equals("bySeq")) {
//        return departmentDao.findOneBySeq(Long.parseLong(filteredKey));
//      }
//
//      throw new WrongAccessException("잘못된 접근입니다.", HOME_REDIRECT_URL);
//    } catch (SQLException sqle) {
//      throw new DataBaseException("데이터베이스 관련 문제가 발생했습니다: " + sqle.getMessage(), sqle);
//    }
//  }

}

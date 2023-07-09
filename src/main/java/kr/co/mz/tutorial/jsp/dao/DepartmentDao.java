package kr.co.mz.tutorial.jsp.dao;

import jakarta.servlet.ServletContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import kr.co.mz.tutorial.jsp.db.QueryManager;
import kr.co.mz.tutorial.jsp.dto.DepartmentDto;

public class DepartmentDao {

  public DataSource dataSource;

  public DepartmentDao(ServletContext servletContext) {
    dataSource = (DataSource) servletContext.getAttribute("dataSource");
  }

  public List<DepartmentDto> findAll() throws SQLException {
    //일반적으로 직원이 프로젝트보다 많다. 그러니 부서와 프로젝트를 가져오고. 직원을 가져오자.
    try (
        var conn = dataSource.getConnection();
        var selectDepartmentWithProjects = conn.prepareStatement(
            QueryManager.getQuery("SELECT_DEPARTMENT_WITH_PROJECTS"));
        var selectDepartmentWithEmployees = conn.prepareStatement(
            QueryManager.getQuery("SELECT_DEPARTMENT_WITH_EMPLOYEES"))
    ) {
      var rs = selectDepartmentWithProjects.executeQuery();
      var departmentList = new ArrayList<DepartmentDto>();
      while (rs.next()) {
        var departmentDto = new DepartmentDto().fromResultSet(rs);
        //리스트에 넣는 코드 필요.

        departmentList.add(departmentDto);
      }

    }
    return null;
  }

  public Optional<DepartmentDto> findOneBySeq(long seq) throws SQLException {
    return Optional.empty();
  }

  public Optional<DepartmentDto> findOneByDepartmentName(String departmentName) throws SQLException {
    return Optional.empty();
  }

  public void insertOne(DepartmentDto dto) {
    try (
        var conn = dataSource.getConnection();
        var pst = conn.prepareStatement(QueryManager.getQuery("INSERT_DEPARTMENT"))
    ) {
      pst.setString(1, dto.getDepartmentName());
      pst.setString(2, dto.getLocation());
      pst.setString(3, dto.getCreatedBy());
      var rs = pst.executeUpdate();
      System.out.println("Insert into department is complete for " + rs + " rows.");
    } catch (SQLException sqle) {
      System.out.println("Failed to insert." + sqle.getMessage());
      sqle.printStackTrace();
    }

  }
}

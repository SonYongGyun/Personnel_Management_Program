package kr.co.mz.tuturial.jsp.test.select;

import java.sql.SQLException;
import kr.co.mz.tutorial.jsp.dao.DepartmentDao;
import kr.co.mz.tutorial.jsp.dao.ProjectDao;
import kr.co.mz.tutorial.jsp.db.HikariPoolFactory;
import kr.co.mz.tutorial.jsp.db.QueryManager;
import kr.co.mz.tutorial.jsp.dto.DepartmentDto;
import kr.co.mz.tutorial.jsp.dto.EmployeeDto;
import kr.co.mz.tutorial.jsp.dto.ProjectDto;

public class SelectDepartment {

  // todo 최적화된 db에서 한번에 찾고. 최적회된 API 로 빠르게 정렬한다.
  public static void main(String[] args) {
    var ds = new HikariPoolFactory().createHikariDataSource();
    var departmentDao = new DepartmentDao(ds);
    var projectDao = new ProjectDao(ds);

    try (
        var conn = ds.getConnection();
        var pst = conn.prepareStatement(QueryManager.getQuery(
            "SELECT_ONE_DEPARTMENT_WITH_PROJECTS_AND_EMPLOYEES_BY_NAME"))
    ) {
      pst.setString(1, "B2B");
      var rs = pst.executeQuery();
      DepartmentDto dto = new DepartmentDto();
      ProjectDto projectDto;
      EmployeeDto employeeDto;
      while (rs.next()) {
        if (dto.getSeq() != new DepartmentDto().fromResultSet(rs).getSeq()) {
          dto = new DepartmentDto().fromResultSet(rs);
        }

        var employeeSeq = rs.getLong("E.seq");
        var existOptionalEmployee = dto.getEmployeesSet().stream().filter(emp -> emp.getSeq() == employeeSeq)
            .findFirst();
        if (existOptionalEmployee.isEmpty()) {
          employeeDto = new EmployeeDto().fromResultSet(rs);
          dto.addEmployee(employeeDto);
        }

        var projectSeq = rs.getLong("P.seq");
        var existOptionalProject = dto.getProjectsSet().stream().filter(pro -> pro.getSeq() == projectSeq)
            .findFirst();
        if (existOptionalProject.isEmpty()) {
          projectDto = new ProjectDto().fromResultSet(rs);
          dto.addProject(projectDto);
        }
      }
      System.out.println("rsult is: " + dto.toString());
    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
  }
}

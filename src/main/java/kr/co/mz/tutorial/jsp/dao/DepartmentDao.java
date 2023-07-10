package kr.co.mz.tutorial.jsp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import kr.co.mz.tutorial.jsp.db.QueryManager;
import kr.co.mz.tutorial.jsp.dto.DepartmentDto;
import kr.co.mz.tutorial.jsp.dto.EmployeeDto;
import kr.co.mz.tutorial.jsp.dto.ProjectDto;

public class DepartmentDao {

  public DataSource dataSource;

  public DepartmentDao(DataSource dataSource) {
    this.dataSource = dataSource;
  }


  public List<DepartmentDto> findAll() throws SQLException {
    try (
        var conn = dataSource.getConnection();
        var pst = conn.prepareStatement(
            QueryManager.getQuery("SELECT_ALL_DEPARTMENT"));
    ) {
      var rs = pst.executeQuery();
      var dtoList = new ArrayList<DepartmentDto>();
      while (rs.next()) {
        var departmentDto = new DepartmentDto().fromResultSet(rs);

        var existOptionalDepartment = dtoList.stream()
            .filter(dep -> dep.getSeq() == departmentDto.getSeq())
            .findFirst();

        var employeesSet = departmentDto.getEmployeesSet();
        var projectsSet = departmentDto.getProjectsSet();
        if (existOptionalDepartment.isEmpty()) {
          dtoList.add(departmentDto);
        } else {
          employeesSet = existOptionalDepartment.get().getEmployeesSet();
          projectsSet = existOptionalDepartment.get().getProjectsSet();
        }

        if (rs.getLong("P.seq") != 0) {
          var projectDto = new ProjectDto().fromResultSet(rs);
          var existOptionalProjectDto = projectsSet.stream()
              .filter(pro -> pro.getSeq() == projectDto.getSeq())
              .findFirst();
          if (existOptionalProjectDto.isEmpty()) {
            projectsSet.add(projectDto);
          }
        }
        //21번째에 E.seq
        if (rs.getLong("E.seq") != 0) {
          var employeeDto = new EmployeeDto().fromResultSet(rs);
          var existOptionalEmployee = employeesSet.stream()
              .filter(emp -> emp.getSeq() == employeeDto.getSeq())
              .findFirst();
          if (existOptionalEmployee.isEmpty()) {
            employeesSet.add(employeeDto);
          }
        }
      }//while

      return dtoList;
    }
  }

  public Optional<DepartmentDto> findOneBySeq(long seq) throws SQLException {
//    try (
//        var conn = dataSource.getConnection();
//        var pst = conn.prepareStatement(QueryManager.getQuery())
//    ) {
//    } catch (SQLException sqle) {
//    }
    return Optional.empty();
  }

  public Optional<DepartmentDto> findOneByDepartmentName(String departmentName) throws SQLException {
    try (
        var conn = dataSource.getConnection();
        var pst = conn.prepareStatement(QueryManager.getQuery(
            "SELECT_ONE_DEPARTMENT_WITH_PROJECTS_AND_EMPLOYEES_BY_NAME"))
    ) {
      pst.setString(1, departmentName);
      var rs = pst.executeQuery();
      DepartmentDto dto = null;
      ProjectDto projectDto;
      EmployeeDto employeeDto;
      while (rs.next()) {
        var currentDto = new DepartmentDto().fromResultSet(rs);
        if (dto == null || dto.getSeq() != currentDto.getSeq()) {
          dto = currentDto;
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
      System.out.println("result is: " + Optional.ofNullable(dto).orElseGet(DepartmentDto::new));
      return Optional.ofNullable(dto);
    }
  }


  public void insertOne(DepartmentDto dto) {
    try (
        var conn = dataSource.getConnection();
        var pst = conn.prepareStatement(QueryManager.getQuery("INSERT_DEPARTMENT"))
    ) {
      System.out.println(dto.getDepartmentName());
      pst.setString(1, dto.getDepartmentName());
      pst.setString(2, dto.getLocation());
      pst.setString(3, dto.getCreatedBy());
      var rs = pst.executeUpdate();
      System.out.println("Insert into department is complete for " + rs + " rows.");
    } catch (SQLException sqle) {
      System.out.println("Failed to insert: " + sqle.getMessage());
      sqle.printStackTrace();
    }

  }
}

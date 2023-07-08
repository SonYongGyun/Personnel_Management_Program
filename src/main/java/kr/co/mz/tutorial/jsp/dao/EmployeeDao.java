package kr.co.mz.tutorial.jsp.dao;

import jakarta.servlet.ServletContext;
import java.sql.SQLException;
import javax.sql.DataSource;
import kr.co.mz.tutorial.jsp.db.QueryManager;
import kr.co.mz.tutorial.jsp.dto.EmployeeDto;

public class EmployeeDao {

  public DataSource dataSource;

  public EmployeeDao(ServletContext servletContext) {
    this.dataSource = (DataSource) servletContext.getAttribute("dataSource");
  }

  public void insertOne(EmployeeDto employeeDto) {
    try (
        var conn = dataSource.getConnection();
        var pst = conn.prepareStatement(QueryManager.getQuery("INSERT_EMPLOYEE"))
    ) {
      pst.setString(1, employeeDto.getEmployeeName());
      pst.setString(2, employeeDto.getPositionIs());
      pst.setString(3, employeeDto.getPhoneNumber());
      pst.setTimestamp(4, employeeDto.getHireDate());
      pst.setLong(5, employeeDto.getDepartmentSeq());
      pst.setLong(6, employeeDto.getManagerSeq());
      pst.setLong(7, employeeDto.getVendorSeq());
      pst.setString(8, employeeDto.getCreatedBy());
      var rs = pst.executeUpdate();
      System.out.println("Insert into employee is complete for " + rs + " rows.");
    } catch (SQLException sqle) {
      System.out.println("Failed to insert." + sqle.getMessage());
      sqle.printStackTrace();
    }
  }

}

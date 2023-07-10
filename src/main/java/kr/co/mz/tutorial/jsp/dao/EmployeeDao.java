package kr.co.mz.tutorial.jsp.dao;

import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import kr.co.mz.tutorial.jsp.db.QueryManager;
import kr.co.mz.tutorial.jsp.dto.EmployeeDto;

public class EmployeeDao {

  public DataSource dataSource;

  public EmployeeDao(DataSource dataSource) {
    this.dataSource = dataSource;
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
      if (employeeDto.getDepartmentSeq() != 0) {
        pst.setLong(5, employeeDto.getDepartmentSeq());
      } else {
        pst.setNull(5, Types.INTEGER);
      }
      if (employeeDto.getManagerSeq() != 0) {
        pst.setLong(6, employeeDto.getManagerSeq());
      } else {
        pst.setNull(6, Types.INTEGER);
      }
      if (employeeDto.getVendorSeq() != 0) {
        pst.setLong(7, employeeDto.getVendorSeq());
      } else {
        pst.setNull(7, Types.INTEGER);
      }
      pst.setString(8, employeeDto.getCreatedBy());
      var rs = pst.executeUpdate();
      System.out.println("Insert into employee is complete for " + rs + " rows.");
    } catch (SQLException sqle) {
      System.out.println("Failed to insert: " + sqle.getMessage());
      sqle.printStackTrace();
    }
  }

  public void deleteOneBySeq(long seq) {
    try (
        var conn = dataSource.getConnection();
        var pst = conn.prepareStatement(QueryManager.getQuery("DELETE_ONE_EMPLOYEE"))
    ) {
      pst.setLong(1, seq);
      var rs = pst.executeUpdate();
      System.out.println("Delete from employee is complete for " + rs + " rows.");

    } catch (SQLException sqle) {
      System.out.println("Failed to delete: " + sqle.getMessage());
      sqle.printStackTrace();
    }
  }

}

package kr.co.mz.tutorial.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import kr.co.mz.tutorial.db.QueryManager;
import kr.co.mz.tutorial.dto.EmployeeDto;

public class EmployeeDao extends AbstractDao {

  private Connection conn;
  private final QueryManager queryManager;

  public EmployeeDao(Connection conn, QueryManager queryManager) {
    this.conn = conn;
    this.queryManager = queryManager;
  }

  public void insertOne(EmployeeDto employeeDto) {
    try (
        var insertEmployeePst = conn.prepareStatement(queryManager.getQuery("INSERT_EMPLOYEE"));
        var updateEmployeesPst = conn.prepareStatement(queryManager.getQuery("UPDATE_DEPARTMENT_TOTAL_EMPLOYEES"))
    ) {
      conn.setAutoCommit(false);
      insertEmployeePst.setString(1, employeeDto.getEmployeeName());
      insertEmployeePst.setString(2, employeeDto.getPositionIs());
      insertEmployeePst.setString(3, employeeDto.getPhoneNumber());
      insertEmployeePst.setTimestamp(4, employeeDto.getHireDate());
      if (employeeDto.getDepartmentSeq() != 0) {
        insertEmployeePst.setLong(5, employeeDto.getDepartmentSeq());
      } else {
        insertEmployeePst.setNull(5, Types.INTEGER);
      }
      if (employeeDto.getManagerSeq() != 0) {
        insertEmployeePst.setLong(6, employeeDto.getManagerSeq());
      } else {
        insertEmployeePst.setNull(6, Types.INTEGER);
      }
      if (employeeDto.getVendorSeq() != 0) {
        insertEmployeePst.setLong(7, employeeDto.getVendorSeq());
      } else {
        insertEmployeePst.setNull(7, Types.INTEGER);
      }
      insertEmployeePst.setString(8, employeeDto.getCreatedBy());
      if (employeeDto.getDepartmentSeq() != 0) {
        updateEmployeesPst.setLong(1, employeeDto.getDepartmentSeq());
        updateEmployeesPst.setLong(2, employeeDto.getDepartmentSeq());
      }
      var rs = insertEmployeePst.executeUpdate();

      conn.commit();

      System.out.println("Insert into employee is complete for " + rs + " rows.");
    } catch (SQLException sqle) {
      System.out.println("Failed to insert: " + sqle.getMessage());
      sqle.printStackTrace();
    }
  }

  public void deleteOneBySeq(long seq) {
    try (
        var pst = conn.prepareStatement(queryManager.getQuery("DELETE_ONE_EMPLOYEE"))
    ) {
      pst.setLong(1, seq);
      var rs = pst.executeUpdate();
      System.out.println("Delete from employee is complete for " + rs + " rows.");

    } catch (SQLException sqle) {
      System.out.println("Failed to delete: " + sqle.getMessage());
      sqle.printStackTrace();
    }
  }

  public Optional<EmployeeDto> findBySeq() {

    return Optional.empty();
  }

  public List<EmployeeDto> findAll() {
    try (
        var pst = conn.prepareStatement(queryManager.getQuery("SELECT_ALL_EMPLOYEE"))
    ) {
      var list = new ArrayList<EmployeeDto>();

      return list;
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      return new ArrayList<EmployeeDto>();
    }

  }

  public void managedBy(long employeeSeq, long managerSeq) {
    try (
        var pst = conn.prepareStatement(queryManager.getQuery("UPDATE_EMPLOYEE_MANAGER_SEQ"))
    ) {
      pst.setLong(1, managerSeq);
      pst.setLong(2, employeeSeq);
      var rs = pst.executeUpdate();

      System.out.println("Update employee is complete for " + rs + " rows.");

    } catch (SQLException sqle) {
      System.out.println("Failed to update: " + sqle.getMessage());
      sqle.printStackTrace();
    }
  }
}

package kr.co.mz.tutorial.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import kr.co.mz.tutorial.dto.EmployeeDto;

public class EmployeeDao extends AbstractDao {

  public EmployeeDao() {
  }

  public void insertOne(EmployeeDto employeeDto) {
    try (
        var conn = getConnection();
        var pst = conn.prepareStatement(queryManager.getQuery("INSERT_EMPLOYEE"))
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
        var conn = getConnection();
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
        var conn = getConnection();
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
        var conn = getConnection();
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

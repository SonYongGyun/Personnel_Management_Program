package kr.co.mz.tutorial.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

public record VendorDto(
    long seq,
    String vendorName,
    String contact,
    Set<EmployeeDto> vendorEmployeesSet,
    String createdBy,
    Timestamp createdTime,
    String modifiedBy,
    Timestamp modifiedTime
) {

  public VendorDto {
    vendorEmployeesSet = new LinkedHashSet<>();
  }

  public VendorDto(String vendorName, String contact, String createdBy) {
    this(0, vendorName, contact, new LinkedHashSet<>(), createdBy, null, null, null);
  }

  public void addVendorEmployee(EmployeeDto employeeDto) {
    vendorEmployeesSet.add(employeeDto);
  }

  public static VendorDto fromResultSet(ResultSet rs) {
    try {
      return new VendorDto(
          rs.getLong("V.seq"),
          rs.getString("V.vendor_name"),
          rs.getString("V.contact"),
          new LinkedHashSet<>(),
          rs.getString("V.created_by"),
          rs.getTimestamp("V.created_time"),
          rs.getString("V.modified_by"),
          rs.getTimestamp("V.modified_time")
      );
    } catch (SQLException sqle) {
      System.out.println("Failed to build vendorDto from resultSet: " + sqle.getMessage());
      return new VendorDto(
          0,
          "",
          "",
          new LinkedHashSet<>(),
          "",
          null,
          "",
          null
      );
    }
  }
}

package kr.co.mz.tutorial.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

public class VendorDto extends AbstractDto {

  private long seq;
  private String vendorName;
  private String contact;
  private Set<EmployeeDto> vendorEmployeesSet = new LinkedHashSet<>();

  public VendorDto() {
  }

  public VendorDto(String vendorName, String contact, String createdBy) {
    this.vendorName = vendorName;
    this.contact = contact;
    this.createdBy = createdBy;
  }

  public long getSeq() {
    return seq;
  }

  public void setSeq(long seq) {
    this.seq = seq;
  }

  public String getVendorName() {
    return vendorName;
  }

  public void setVendorName(String vendorName) {
    this.vendorName = vendorName;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Timestamp getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Timestamp createdTime) {
    this.createdTime = createdTime;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public Timestamp getModifiedTime() {
    return modifiedTime;
  }

  public void setModifiedTime(Timestamp modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

  public Set<EmployeeDto> getVendorEmployeesSet() {
    return vendorEmployeesSet;
  }

  public void setVendorEmployeesSet(Set<EmployeeDto> vendorEmployeesSet) {
    this.vendorEmployeesSet = vendorEmployeesSet;
  }

  public void addVendorEmployee(EmployeeDto employeeDto) {
    vendorEmployeesSet.add(employeeDto);
  }

  public VendorDto fromResultSet(ResultSet rs) {
    try {
      var vendorDto = new VendorDto();
      vendorDto.setSeq(rs.getLong("V.seq"));
      vendorDto.setVendorName(rs.getString("V.vendor_name"));
      vendorDto.setContact(rs.getString("V.contact"));
      vendorDto.setCreatedBy(rs.getString("V.created_by"));
      vendorDto.setCreatedTime(rs.getTimestamp("V.created_time"));
      vendorDto.setModifiedBy(rs.getString("V.modified_by"));
      vendorDto.setModifiedTime(rs.getTimestamp("V.modified_time"));
      return vendorDto;
    } catch (SQLException sqle) {
      System.out.println("Failed to build vendorDto from resultSet: " + sqle.getMessage());
      return new VendorDto();
    }
  }

}

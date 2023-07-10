package kr.co.mz.tutorial.jsp.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

public class EmployeeDto extends AbstractDto {

  private long seq;
  private String employeeName;
  private String positionIs;
  private String phoneNumber;
  private Timestamp hireDate;
  private long departmentSeq;
  private long managerSeq;
  private long vendorSeq;
  private Set<EmployeeDto> managingEmployeesSet = new LinkedHashSet<>();

  private Set<ProjectDto> joinedProjectSet = new LinkedHashSet<>();

  public EmployeeDto() {
  }

  public EmployeeDto(String employeeName, String positionIs, String phoneNumber, Timestamp hireDate, long departmentSeq,
      long managerSeq, long vendorSeq) {
    this.employeeName = employeeName;
    this.positionIs = positionIs;
    this.phoneNumber = phoneNumber;
    this.hireDate = hireDate;
    this.departmentSeq = departmentSeq;
    this.managerSeq = managerSeq;
    this.vendorSeq = vendorSeq;
  }

  public long getSeq() {
    return seq;
  }

  public void setSeq(long seq) {
    this.seq = seq;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String getPositionIs() {
    return positionIs;
  }

  public void setPositionIs(String positionIs) {
    this.positionIs = positionIs;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Timestamp getHireDate() {
    return hireDate;
  }

  public void setHireDate(Timestamp hireDate) {
    this.hireDate = hireDate;
  }

  public long getDepartmentSeq() {
    return departmentSeq;
  }

  public void setDepartmentSeq(long departmentSeq) {
    this.departmentSeq = departmentSeq;
  }

  public long getManagerSeq() {
    return managerSeq;
  }

  public void setManagerSeq(long managerSeq) {
    this.managerSeq = managerSeq;
  }

  public long getVendorSeq() {
    return vendorSeq;
  }

  public void setVendorSeq(long vendorSeq) {
    this.vendorSeq = vendorSeq;
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

  public Set<EmployeeDto> getManagingEmployeesSet() {
    return managingEmployeesSet;
  }

  public void setManagingEmployeesSet(Set<EmployeeDto> managingEmployeesSet) {
    this.managingEmployeesSet = managingEmployeesSet;
  }

  public void addManagingEmployee(EmployeeDto employeeDto) {
    managingEmployeesSet.add(employeeDto);
  }

  public Set<ProjectDto> getJoinedProjectSet() {
    return joinedProjectSet;
  }

  public void setJoinedProjectSet(Set<ProjectDto> joinedProjectSet) {
    this.joinedProjectSet = joinedProjectSet;
  }

  public void addJoinedProjectSet(ProjectDto projectDto) {
    joinedProjectSet.add(projectDto);
  }

  public EmployeeDto fromResultSet(ResultSet rs) {
    try {
      var employeeDto = new EmployeeDto();
      employeeDto.setSeq(rs.getLong("E.seq"));
      employeeDto.setEmployeeName(rs.getString("E.employee_name"));
      employeeDto.setPositionIs(rs.getString("E.position_is"));
      employeeDto.setPhoneNumber(rs.getString("E.phone_number"));
      employeeDto.setHireDate(rs.getTimestamp("E.hire_date"));
      employeeDto.setDepartmentSeq(rs.getLong("E.department_seq"));
      employeeDto.setManagerSeq(rs.getLong("E.manager_seq"));
      employeeDto.setVendorSeq(rs.getLong("E.vendor_seq"));

      employeeDto.setCreatedBy(rs.getString("E.created_by"));
      employeeDto.setCreatedTime(rs.getTimestamp("E.created_time"));
      employeeDto.setModifiedBy(rs.getString("E.modified_by"));
      employeeDto.setModifiedTime(rs.getTimestamp("E.modified_time"));

      return employeeDto;
    } catch (SQLException sqle) {
      System.out.println("Failed to build employeeDto from resultSet: " + sqle.getMessage());
      sqle.printStackTrace();
      return new EmployeeDto();
    }
  }

  @Override
  public String toString() {
    return "EmployeeDto{" +
        "seq=" + seq +
        ", employeeName='" + employeeName + '\'' +
        ", positionIs='" + positionIs + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", hireDate=" + hireDate +
        ", departmentSeq=" + departmentSeq +
        ", managerSeq=" + managerSeq +
        ", vendorSeq=" + vendorSeq +
        ", managingEmployeesSet=" + managingEmployeesSet +
        ", joinedProjectSet=" + joinedProjectSet +
        ", createdBy='" + createdBy + '\'' +
        ", createdTime=" + createdTime +
        ", modifiedBy='" + modifiedBy + '\'' +
        ", modifiedTime=" + modifiedTime +
        '}';
  }
}

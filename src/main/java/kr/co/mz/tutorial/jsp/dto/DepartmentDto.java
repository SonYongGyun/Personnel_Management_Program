package kr.co.mz.tutorial.jsp.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DepartmentDto extends AbstractDto {

  private long seq;
  private String departmentName;
  private String location;
  private long totalEmployees;
  private long totalProjects;

  private final Set<EmployeeDto> employeesSet = new LinkedHashSet<>();
  private final Set<ProjectDto> projectsSet = new LinkedHashSet<>();


  public DepartmentDto() {
  }

  public DepartmentDto(String departmentId, String location, String createdBy) {
    this.departmentName = departmentId;
    this.location = location;
    this.createdBy = createdBy;
  }

  public long getSeq() {
    return seq;
  }

  public void setSeq(long seq) {
    this.seq = seq;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public long getTotalEmployees() {
    return totalEmployees;
  }

  public void setTotalEmployees(long totalEmployees) {
    this.totalEmployees = totalEmployees;
  }

  public long getTotalProjects() {
    return totalProjects;
  }

  public void setTotalProjects(long totalProjects) {
    this.totalProjects = totalProjects;
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

  public Set<EmployeeDto> getEmployeesSet() {
    return employeesSet;
  }

  public void addEmployee(EmployeeDto employeeDto) {
    employeesSet.add(employeeDto);
  }

  public Set<ProjectDto> getProjectsSet() {
    return projectsSet;
  }

  public void addProject(ProjectDto projectDto) {
    projectsSet.add(projectDto);
  }

  public DepartmentDto fromResultSet(ResultSet rs) {
    try {
      var departmentDto = new DepartmentDto();
      departmentDto.setSeq(rs.getLong("D.seq"));
      departmentDto.setDepartmentName(rs.getString("D.department_name"));
      departmentDto.setLocation(rs.getString("D.location"));
      departmentDto.setTotalEmployees(rs.getInt("D.total_employees"));
      departmentDto.setTotalProjects(rs.getInt("D.total_projects"));

      departmentDto.setCreatedBy(rs.getString("D.created_by"));
      departmentDto.setCreatedTime(rs.getTimestamp("D.created_time"));
      departmentDto.setModifiedBy(rs.getString("D.modified_by"));
      departmentDto.setModifiedTime(rs.getTimestamp("D.modified_time"));

      return departmentDto;
    } catch (SQLException sqle) {
      System.out.println("Failed to build departmentDto from resultSet: " + sqle.getMessage());
      return new DepartmentDto();
    }
  }

  @Override
  public String toString() {
    return "DepartmentDto{" +
        "seq=" + seq +
        ", departmentName='" + departmentName + '\'' +
        ", location='" + location + '\'' +
        ", totalEmployees=" + totalEmployees +
        ", totalProjects=" + totalProjects +
        ", createdBy='" + createdBy + '\'' +
        ", createdTime=" + createdTime +
        ", modifiedBy='" + modifiedBy + '\'' +
        ", modifiedTime=" + modifiedTime +
        ",\nemployeesSet= \n" + employeesSet.stream().map(EmployeeDto::toString).collect(Collectors.joining("\n ")) +
        "\nprojectsSet=\n " + projectsSet.stream().map(ProjectDto::toString).collect(Collectors.joining("\n ")) +
        "\n}\n\n";
  }
}

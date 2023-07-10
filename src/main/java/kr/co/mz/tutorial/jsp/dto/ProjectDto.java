package kr.co.mz.tutorial.jsp.dto;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

public class ProjectDto extends AbstractDto {

  private long seq;
  private String projectName;
  private String projectDescription;
  private String projectStatus;
  private BigDecimal budget;
  private Timestamp startDate;
  private Timestamp endDate;
  private Set<DepartmentDto> joinedDepartmentsSet = new LinkedHashSet<>();
  private Set<EmployeeDto> joinedEmployeesSet = new LinkedHashSet<>();

  public ProjectDto() {
  }

  public ProjectDto(String projectName, String projectDescription, String projectStatus, BigDecimal budget,
      Timestamp startDate, String createdBy) {
    this.projectName = projectName;
    this.projectDescription = projectDescription;
    this.projectStatus = projectStatus;
    this.budget = budget;
    this.startDate = startDate;
    this.createdBy = createdBy;
  }

  public long getSeq() {
    return seq;
  }

  public void setSeq(long seq) {
    this.seq = seq;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getProjectDescription() {
    return projectDescription;
  }

  public void setProjectDescription(String projectDescription) {
    this.projectDescription = projectDescription;
  }

  public String getProjectStatus() {
    return projectStatus;
  }

  public void setProjectStatus(String projectStatus) {
    this.projectStatus = projectStatus;
  }

  public BigDecimal getBudget() {
    return budget;
  }

  public void setBudget(BigDecimal budget) {
    this.budget = budget;
  }

  public Timestamp getStartDate() {
    return startDate;
  }

  public void setStartDate(Timestamp startDate) {
    this.startDate = startDate;
  }

  public Timestamp getEndDate() {
    return endDate;
  }

  public void setEndDate(Timestamp endDate) {
    this.endDate = endDate;
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

  public Set<DepartmentDto> getJoinedDepartmentsSet() {
    return joinedDepartmentsSet;
  }

  public void setJoinedDepartmentsSet(Set<DepartmentDto> joinedDepartmentsSet) {
    this.joinedDepartmentsSet = joinedDepartmentsSet;
  }

  public void addJoinedDepartment(DepartmentDto departmentDto) {
    joinedDepartmentsSet.add(departmentDto);
  }

  public Set<EmployeeDto> getJoinedEmployeesSet() {
    return joinedEmployeesSet;
  }

  public void setJoinedEmployeesSet(Set<EmployeeDto> joinedEmployeesSet) {
    this.joinedEmployeesSet = joinedEmployeesSet;
  }

  public void addJoinedEmployee(EmployeeDto employeeDto) {
    joinedEmployeesSet.add(employeeDto);
  }


  public ProjectDto fromResultSet(ResultSet rs) {
    try {
      var projectDto = new ProjectDto();
      projectDto.setSeq(rs.getLong("P.seq"));
      projectDto.setProjectName(rs.getString("P.project_name"));
      projectDto.setProjectDescription(rs.getString("P.project_description"));
      projectDto.setProjectStatus(rs.getString("P.project_status"));
      projectDto.setBudget(rs.getBigDecimal("P.budget"));
      projectDto.setStartDate(rs.getTimestamp("P.start_date"));
      projectDto.setEndDate(rs.getTimestamp("P.end_date"));

      projectDto.setCreatedBy(rs.getString("P.created_by"));
      projectDto.setCreatedTime(rs.getTimestamp("P.created_time"));
      projectDto.setModifiedBy(rs.getString("P.modified_by"));
      projectDto.setModifiedTime(rs.getTimestamp("P.modified_time"));
      return projectDto;
    } catch (SQLException sqle) {
      System.out.println("Failed to build projectDto from resultSet: " + sqle.getMessage());
      sqle.printStackTrace();
      return new ProjectDto();
    }
  }

  @Override
  public String toString() {
    return "ProjectDto{" +
        "seq=" + seq +
        ", projectName='" + projectName + '\'' +
        ", projectDescription='" + projectDescription + '\'' +
        ", projectStatus='" + projectStatus + '\'' +
        ", budget=" + budget +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", joinedDepartmentsSet=" + joinedDepartmentsSet +
        ", joinedEmployeesSet=" + joinedEmployeesSet +
        ", createdBy='" + createdBy + '\'' +
        ", createdTime=" + createdTime +
        ", modifiedBy='" + modifiedBy + '\'' +
        ", modifiedTime=" + modifiedTime +
        '}';
  }


}

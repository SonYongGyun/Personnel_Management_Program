package kr.co.mz.tuturial.jsp.test.create;

import kr.co.mz.tutorial.jsp.dto.DepartmentDto;

public class CreateDepartmentDto {

  public DepartmentDto nameB2B() {
    var dto = new DepartmentDto();
    dto.setDepartmentName("B2B");
    dto.setLocation("6F");
    dto.setCreatedBy("admin");
    return dto;
  }

  public DepartmentDto nameTaskForce1() {
    var dto = new DepartmentDto();
    dto.setDepartmentName("TaskForce1");
    dto.setLocation("9F");
    dto.setCreatedBy("admin");
    return dto;
  }

  public DepartmentDto nameTaskForce2() {
    var dto = new DepartmentDto();
    dto.setDepartmentName("TaskForce2");
    dto.setLocation("7F");
    dto.setCreatedBy("admin");
    return dto;
  }
}

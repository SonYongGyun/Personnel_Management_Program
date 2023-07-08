package kr.co.mz.tuturial.jsp.test;

import java.sql.Timestamp;
import kr.co.mz.tutorial.jsp.dto.EmployeeDto;

public class CreateEmployeeDto {

  public EmployeeDto nameSlaveNoledge() {
    var dto = new EmployeeDto();
    dto.setEmployeeName("SlaveNoledge");
    dto.setPositionIs("NewSlave");
    dto.setPhoneNumber("010-xxxx-xxxx");
    dto.setHireDate(Timestamp.valueOf("2023-06-01"));
    dto.setDepartmentSeq();// todo department
    dto.setManagerSeq();
    dto.setCreatedBy("admin");
    return dto;
  }

  public EmployeeDto nameSlaveNotry() {
    var dto = new EmployeeDto();
    dto.setEmployeeName("SlaveNotry");
    dto.setPositionIs("NewSlave");
    dto.setPhoneNumber("010-xxxx-xxxx");
    dto.setHireDate(Timestamp.valueOf("2023-06-01"));
    dto.setDepartmentSeq();// todo department
    dto.setManagerSeq();
    dto.setCreatedBy("admin");
    return dto;
  }

  public EmployeeDto nameMaster() {
    var dto = new EmployeeDto();
    dto.setEmployeeName("Master");
    dto.setPositionIs("manager");
    dto.setPhoneNumber("010-xxxx-xxxx");
    dto.setHireDate(Timestamp.valueOf("2015-04-19"));
    dto.setDepartmentSeq();// todo department
    dto.setCreatedBy("admin");
    return dto;
  }

  public EmployeeDto nameBoss() {
    var dto = new EmployeeDto();
    dto.setEmployeeName("Boss");
    dto.setPositionIs("Boss");
    dto.setPhoneNumber("010-bbbb-bbbb");
    dto.setHireDate(Timestamp.valueOf("1999-04-04"));
    dto.setDepartmentSeq();// todo department
    dto.setCreatedBy("admin");
    return dto;
  }

  public EmployeeDto nameSlaveGoinghome() {
    var dto = new EmployeeDto();
    dto.setEmployeeName("SlaveGoinghome");
    dto.setPositionIs("NewSlave");
    dto.setPhoneNumber("010-xxxx-xxxx");
    dto.setHireDate(Timestamp.valueOf("2023-06-01"));
    dto.setDepartmentSeq();// todo department
    dto.setManagerSeq();
    dto.setVendorSeq();
    dto.setCreatedBy("admin");
    return dto;
  }

  public EmployeeDto nameVendoerSlave1() {
    var dto = new EmployeeDto();
    dto.setEmployeeName("VendoerSlave1");
    dto.setPositionIs("Dispatch");
    dto.setPhoneNumber("010-xxxx-xxxx");
    dto.setHireDate(Timestamp.valueOf("2023-03-01"));
    dto.setDepartmentSeq();// todo department
    dto.setManagerSeq();
    dto.setVendorSeq();
    dto.setCreatedBy("admin");
    return dto;
  }

  public EmployeeDto nameVendoerSlave2() {
    var dto = new EmployeeDto();
    dto.setEmployeeName("VendoerSlave2");
    dto.setPositionIs("Dispatch");
    dto.setPhoneNumber("010-xxxx-xxxx");
    dto.setHireDate(Timestamp.valueOf("2023-03-01"));
    dto.setDepartmentSeq();// todo department
    dto.setManagerSeq();
    dto.setVendorSeq();
    dto.setCreatedBy("admin");
    return dto;
  }
}

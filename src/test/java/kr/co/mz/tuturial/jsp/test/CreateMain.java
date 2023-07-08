package kr.co.mz.tuturial.jsp.test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import kr.co.mz.tutorial.jsp.dto.EmployeeDto;

public class CreateMain {

  public static void main(String[] args) throws IOException, SQLException {
    // 부서 3개, 프로젝트 3개, 직원 5명, 협력사 2개.
    for (int i = 0; i < 100; i++) {
      var dto = new EmployeeDto();
      dto.setEmployeeName("SlaveNoledge");
      dto.setPositionIs("NewSlave");
      dto.setPhoneNumber("010-xxxx-xxxx");
      dto.setHireDate(Timestamp.valueOf("2023-06-01"));
      dto.setDepartmentSeq();// todo department
      dto.setManagerSeq();
      dto.setCreatedBy("admin");
    }

  }

}

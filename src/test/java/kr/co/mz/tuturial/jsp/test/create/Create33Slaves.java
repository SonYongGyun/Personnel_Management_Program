package kr.co.mz.tuturial.jsp.test.create;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import kr.co.mz.tutorial.jsp.dao.EmployeeDao;
import kr.co.mz.tutorial.jsp.db.HikariPoolFactory;
import kr.co.mz.tutorial.jsp.dto.EmployeeDto;

public class Create33Slaves {

  public static void main(String[] args) throws IOException, SQLException {
    // 부서 3개, 프로젝트 3개, 직원 5명, 협력사 2개.
    var ds = new HikariPoolFactory().createHikariDataSource();
    var empDao = new EmployeeDao(ds);
    var empDtoC = new CreateEmployeeDto();
//
    for (int i = 0; i < 33; i++) {
      var dto = new EmployeeDto();
      dto.setEmployeeName("SlaveNoledge" + String.format("%02d", i + 1));
      dto.setPositionIs("NewSlave");
      dto.setPhoneNumber("010-xxxx-xx" + String.format("%02d", i));
      dto.setHireDate(Timestamp.valueOf("2023-06-01 08:00:00"));
      dto.setCreatedBy("admin");
      empDao.insertOne(dto);
    }
//    empDao.insertOne(empDtoC.nameBoss());
//    empDao.insertOne(empDtoC.nameMaster());

  }
}

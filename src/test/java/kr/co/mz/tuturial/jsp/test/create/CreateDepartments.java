package kr.co.mz.tuturial.jsp.test.create;

import java.io.IOException;
import java.sql.SQLException;
import kr.co.mz.tutorial.dao.DepartmentDao;
import kr.co.mz.tutorial.db.HikariPoolFactory;

public class CreateDepartments {

  public static void main(String[] args) throws IOException, SQLException {
    // 부서 3개, 프로젝트 3개, 직원 5명, 협력사 2개.
    var ds = new HikariPoolFactory().createHikariDataSource();

    var depDtoC = new CreateDepartmentDto();
    var departmentDao = new DepartmentDao();
    departmentDao.insertOne(depDtoC.nameB2B());
    departmentDao.insertOne(depDtoC.nameTaskForce1());
    departmentDao.insertOne(depDtoC.nameTaskForce2());
  }

}

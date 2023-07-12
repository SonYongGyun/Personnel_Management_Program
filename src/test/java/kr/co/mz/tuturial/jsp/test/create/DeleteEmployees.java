package kr.co.mz.tuturial.jsp.test.create;

import java.io.IOException;
import java.sql.SQLException;
import kr.co.mz.tutorial.dao.EmployeeDao;
import kr.co.mz.tutorial.db.HikariPoolFactory;
import kr.co.mz.tutorial.db.QueryManager;

public class DeleteEmployees {

  public static void main(String[] args) throws IOException, SQLException {
    // 부서 3개, 프로젝트 3개, 직원 5명, 협력사 2개.
    var ds = new HikariPoolFactory().createHikariDataSource();
    var queryManager = new QueryManager();
    var empDao = new EmployeeDao(ds.getConnection(), queryManager);

    for (int i = 3; i < 36; i++) {
      empDao.deleteOneBySeq(i);
    }
  }

}

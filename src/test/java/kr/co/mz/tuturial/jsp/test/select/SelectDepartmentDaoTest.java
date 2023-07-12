package kr.co.mz.tuturial.jsp.test.select;

import java.sql.SQLException;
import kr.co.mz.tutorial.dao.DepartmentDao;
import kr.co.mz.tutorial.db.HikariPoolFactory;
import kr.co.mz.tutorial.db.QueryManager;
import kr.co.mz.tutorial.dto.DepartmentDto;

public class SelectDepartmentDaoTest {

  // todo 최적화된 db에서 한번에 찾고. 최적회된 API 로 빠르게 정렬한다.
  public static void main(String[] args) throws SQLException {
    var ds = new HikariPoolFactory().createHikariDataSource();

    var queryManager = new QueryManager();

    var departmentDao = new DepartmentDao(ds.getConnection(), queryManager);

    try {
      var list = departmentDao.findAll();
      for (DepartmentDto d : list) {
        System.out.println(d);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}

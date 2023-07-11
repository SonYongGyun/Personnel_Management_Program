package kr.co.mz.tuturial.jsp.test.create;

import java.io.IOException;
import java.sql.SQLException;
import kr.co.mz.tutorial.dao.ProjectDao;
import kr.co.mz.tutorial.db.HikariPoolFactory;

public class DeleteProject {

  public static void main(String[] args) throws IOException, SQLException {
    // 부서 3개, 프로젝트 3개, 직원 5명, 협력사 2개.
    var ds = new HikariPoolFactory().createHikariDataSource();
    var projectDao = new ProjectDao();

    for (int i = 5; i <= 7; i++) {
      projectDao.deleteOneBySeq(i);
    }

  }
}

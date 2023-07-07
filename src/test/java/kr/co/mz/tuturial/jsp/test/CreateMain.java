package kr.co.mz.tuturial.jsp.test;

import java.io.IOException;
import java.sql.SQLException;
import kr.co.mz.tutorial.jsp.db.HikariPoolFactory;
import kr.co.mz.tutorial.jsp.db.QueryManager;

public class CreateMain {

  public static void main(String[] args) throws IOException, SQLException {
    var hkariPoolFactory = new HikariPoolFactory();
    var dataSource = hkariPoolFactory.createHikariDataSource();
    try (var connection = dataSource.getConnection()) {
      var pst = connection.prepareStatement(QueryManager.getQuery("INSERT_DEPARTMENT4"));
      pst.setString(1, "B1B");
      pst.setString(2, "None");
      pst.setString(3, "admin");
      pst.setString(4, "admin");

      var rs = pst.executeUpdate();
      connection.commit();
      System.out.println("Execution result 1 means success: " + rs);
      pst.close();
    }
  }

}

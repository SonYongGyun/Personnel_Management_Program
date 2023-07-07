package kr.co.mz.tuturial.jsp.test;

import java.sql.SQLException;
import kr.co.mz.tutorial.jsp.db.HikariPoolFactory;
import kr.co.mz.tutorial.jsp.db.QueryManager;
import kr.co.mz.tutorial.jsp.dto.DepartmentDto;

public class Insert1Department {

  public void insert(DepartmentDto dto) {
    var hikariPoolFactory = new HikariPoolFactory();
    var dataSource = hikariPoolFactory.createHikariDataSource();
    try (var conn = dataSource.getConnection()) {
      var pst = conn.prepareStatement(QueryManager.getQuery("INSERT_DEPARTMENT4"));
      pst.setString(1, dto.getDepartmentName());
      pst.setString(2, dto.getLocation());
      pst.setString(3, dto.getCreatedBy());
      pst.setString(4, dto.getModifiedBy());
      var rs = pst.executeUpdate();
      System.out.println("Insert complete for " + rs + " rows.");
    } catch (SQLException sqle) {
      System.out.println("Failed to insert." + sqle.getMessage());
      sqle.printStackTrace();
    }

  }
}

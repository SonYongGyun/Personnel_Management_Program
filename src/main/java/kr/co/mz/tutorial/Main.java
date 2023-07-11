package kr.co.mz.tutorial;

import java.sql.SQLException;
import kr.co.mz.tutorial.db.HikariPoolFactory;

public class Main {

  public static void main(String[] args) throws SQLException {
    var hpf = new HikariPoolFactory();
    var ds = hpf.createHikariDataSource();
    var conn = ds.getConnection();
    String sql = "select * from department";

    var preparedStatement = conn.prepareStatement(sql);
    var rs = preparedStatement.executeQuery();
    while (rs.next()) {
      System.out.println("wdwdwd");
      System.out.println(rs.getString(1));
      System.out.println(rs.getString(2));
      System.out.println(rs.getString(3));
      System.out.println(rs.getString(4));
    }
  }
}
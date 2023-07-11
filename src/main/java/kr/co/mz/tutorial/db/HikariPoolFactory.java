package kr.co.mz.tutorial.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;

public class HikariPoolFactory {

  public DataSource createHikariDataSource() {
    var props = new Properties();
    InputStream is = null;
    try {
      is = getClass().getClassLoader().getResourceAsStream("db/hikari.properties");
      if (is == null) {
        System.out.println("Could not find 'db/hikari.properties' in the classpath");
      } else {
        props.load(is);
      }
    } catch (IOException ioe) {
      System.out.println("Could not load 'db/hikari.properties' from the classpath");
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    var config = new HikariConfig(props);
    var ds = new HikariDataSource(config);
    return ds;
  }
}

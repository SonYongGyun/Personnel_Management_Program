package kr.co.mz.tutorial.jsp.db;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HikariCPListener implements ServletContextListener {

  private HikariDataSource dataSource;

  @Override
  public void contextInitialized(ServletContextEvent sce) {

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
    dataSource = new HikariDataSource(config);

    sce.getServletContext().setAttribute("dataSource", dataSource);//설정추가.
  }


  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    if (dataSource != null) {
      dataSource.close();
    }
    // MySQL 드라이버의 백그라운드 스레드 중지

    AbandonedConnectionCleanupThread.checkedShutdown();

  }

}

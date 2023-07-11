package kr.co.mz.tutorial.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import kr.co.mz.tutorial.db.QueryManager;

public class QueryManagerListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    var queryManager = new QueryManager();
    sce.getServletContext().setAttribute("queryManager", queryManager);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    ServletContextListener.super.contextDestroyed(sce);
  }
}

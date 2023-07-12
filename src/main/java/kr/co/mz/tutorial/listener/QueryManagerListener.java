package kr.co.mz.tutorial.listener;

import static kr.co.mz.tutorial.Constants.DATASOURCE_CONTEXT_KEY;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import kr.co.mz.tutorial.db.QueryManager;

public class QueryManagerListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    var queryManager = new QueryManager();
    sce.getServletContext().setAttribute(DATASOURCE_CONTEXT_KEY, queryManager);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    ServletContextListener.super.contextDestroyed(sce);
  }
}

package kr.co.mz.tutorial.servlet.filter;

import static kr.co.mz.tutorial.Constants.FILTERED_PARAMETER_KEY;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import kr.co.mz.tutorial.exception.AlertQueryStringValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryStringFilter implements Filter {

  private static final Logger LOGGER = LoggerFactory.getLogger(QueryStringFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
    LOGGER.debug("Query Filter is registered.");
  }

  @Override
  public void destroy() {
    Filter.super.destroy();

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    var key_ = request.getParameter("key");
    String key = "";
    if (key_ == null || key_.isEmpty()) {
      LOGGER.debug("An empty key.");
      throw new AlertQueryStringValidationException("Please insert exact key to find what you want.");
    }
    key = key_;
    request.setAttribute(FILTERED_PARAMETER_KEY, key);
    LOGGER.debug("Set key={} ", key);
    chain.doFilter(request, response);
  }
}

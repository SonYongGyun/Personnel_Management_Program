package kr.co.mz.tutorial.servlet.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DepartmentQueryStringFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
    System.out.println("Start Query Filtering");
  }

  @Override
  public void destroy() {
    System.out.println("Query Filtering is Done");
    Filter.super.destroy();

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    var key_ = request.getParameter("key");
    String key = "";
    if (key_ == null || key_.isEmpty()) {
      System.out.println("No Key");
      var httpResponse = (HttpServletResponse) response;
      httpResponse.sendRedirect("/home");
    }
    key = key_;
    request.setAttribute("filteredKey", key);
    chain.doFilter(request, response);

  }
}

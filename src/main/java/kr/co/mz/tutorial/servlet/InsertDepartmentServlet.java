package kr.co.mz.tutorial.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertDepartmentServlet extends HttpServlet {

  public static final Logger LOGGER = LoggerFactory.getLogger(InsertDepartmentServlet.class);

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //부서하나를 등록할때는, 부서도 등록하고 프로젝트도 등록하고 직원도 등록한다. 아무도없을수도있고 아무프로젝트도 없을수도있다.

  }
}

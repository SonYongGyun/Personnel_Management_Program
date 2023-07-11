package kr.co.mz.tutorial.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HomeServlet extends HttpServlet {

  public static final String JSP_PATH = "/WEB-INF/views/home.jsp";

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    var rd = request.getRequestDispatcher(JSP_PATH);
    rd.forward(request, response);

  }


}


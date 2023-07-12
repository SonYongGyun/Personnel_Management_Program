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
    response.setContentType("text/html;charset=UTF-8");

    var out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
    out.println("<title>Simple Servlet Container</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Welcome to Simple Servlet Container</h1>");
    out.println("<p><a href=\"departmentList\">departmentList</a></p>");
    out.println("<form action=\"findOneDepartment\" method=\"get\"");
    out.println("<div>");
    out.println("<input type=\"text\" name=\"key\"/>");
    out.println("</div>");
    out.println("<div>");
    out.println("<button type=\"submit\" value=\"findByName\" >Find By Name</button>");
    out.println("<button type=\"submit\" value=\"findBySeq\" >Find By Seq</button>");
    out.println("</div>");
    out.println("</form>");
    out.println("<p><a href=\"employeeList\">employeeList</a></p>");
    out.println("<p><a href=\"projectList\">projectList</a></p>");
    out.println("<p><a href=\"VendorList\">vendorList</a></p>");
    out.println("<p>아아 한글쳌</p>");
    out.println("</body>");
    out.println("</html>");
  }
}
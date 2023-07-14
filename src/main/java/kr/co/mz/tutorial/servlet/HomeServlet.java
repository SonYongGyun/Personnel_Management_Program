package kr.co.mz.tutorial.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HomeServlet extends HttpServlet {

  private static final String PAGE_CONTENTS = """
          <!DOCTYPE html>
          <html>
          <head>
          <meta http-equiv=content-type content=text/html; charset=UTF-8>
          <title>Simple Company Structure</title>
          </head>
          <body>
          <h1>Welcome to Simple Servlet Container</h1>
          <p><a href="/list/department">departmentList</a></p>
          <form action="/find-one/department" method="get">
          <div>
          <input type="text" name="key"></input>
          </div>
          <div>
          <button type="submit" name="find" value="byName" >Find By Name</button>
          <button type="submit" name="find" value="bySeq">Find By Seq</button>
          </div>
          </form>
          <p><a href="/list/employee">employeeList</a></p>
          <p><a href="/list/project">projectList</a></p>
          <p><a href="/list/Vendor">vendorList</a></p>
          <p>아아 한글쳌</p>
          </body>
          </html>      
      """.stripIndent();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (
        var out = response.getWriter();
    ) {
      out.println(PAGE_CONTENTS);
    }

  }
}
package kr.co.mz.tutorial.jsp.dao;

import jakarta.servlet.ServletContext;
import java.sql.SQLException;
import javax.sql.DataSource;
import kr.co.mz.tutorial.jsp.db.QueryManager;
import kr.co.mz.tutorial.jsp.dto.ProjectDto;

public class ProjectDao {

  public DataSource dataSource;

  public ProjectDao(ServletContext servletContext) {
    this.dataSource = (DataSource) servletContext.getAttribute("dataSource");
  }

  public void insert(ProjectDto projectDto) {
    try (
        var conn = dataSource.getConnection();
        var pst = conn.prepareStatement(QueryManager.getQuery("INSERT_PROJECT"))
    ) {
      pst.setString(1, projectDto.getProjectName());
      pst.setString(2, projectDto.getProjectDescription());
      pst.setString(3, projectDto.getProjectStatus());
      pst.setBigDecimal(4, projectDto.getBudget());
      pst.setString(5, projectDto.getCreatedBy());
      var rs = pst.executeUpdate();
      System.out.println("Insert into project is complete for " + rs + " rows.");
    } catch (SQLException sqle) {
      System.out.println("Failed to insert." + sqle.getMessage());
      sqle.printStackTrace();
    }
  }
}

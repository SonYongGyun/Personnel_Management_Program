package kr.co.mz.tutorial.dao;

import java.sql.Connection;
import java.sql.SQLException;
import kr.co.mz.tutorial.db.QueryManager;
import kr.co.mz.tutorial.dto.ProjectDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProjectDao.class);
  private Connection conn;
  private final QueryManager queryManager;

  public ProjectDao(Connection conn, QueryManager queryManager) {
    this.conn = conn;
    this.queryManager = queryManager;
  }

  public void insertOne(ProjectDto projectDto) throws SQLException {
    var acquiredQuery = queryManager.getQuery("INSERT_PROJECT");
    if (!acquiredQuery.isEmpty()) {
      LOGGER.debug("Query is\n{}", acquiredQuery);
    }
    try (
        var pst = conn.prepareStatement(acquiredQuery)
    ) {
      pst.setString(1, projectDto.getProjectName());
      pst.setString(2, projectDto.getProjectDescription());
      pst.setString(3, projectDto.getProjectStatus());
      pst.setBigDecimal(4, projectDto.getBudget());
      pst.setString(5, projectDto.getCreatedBy());
      var rs = pst.executeUpdate();
      LOGGER.debug("Insert into project is complete for " + rs + " rows.");
    }
  }

  public void deleteOneBySeq(long seq) throws SQLException {
    try (
        var pst = conn.prepareStatement(queryManager.getQuery("DELETE_ONE_PROJECT"))
    ) {
      pst.setLong(1, seq);
      var rs = pst.executeUpdate();
      System.out.println("Delete from project is complete for " + rs + " rows.");
    }
  }

  public void assignToDepartment(long departmentSeq, long projectSeq) throws SQLException {
    try (
        var pst = conn.prepareStatement(queryManager.getQuery("INSERT_DP_RELATIONSHIP"))
    ) {
      pst.setLong(1, departmentSeq);
      pst.setLong(2, projectSeq);
      var rs = pst.executeUpdate();

      System.out.println("Project is assigned for " + rs + " department.");
    }
  }

  public void assignToEmployee(long employeeSeq, long projectSeq) throws SQLException {
    try (
        var pst = conn.prepareStatement(queryManager.getQuery("INSERT_EP_RELATIONSHIP"))
    ) {
      pst.setLong(1, employeeSeq);
      pst.setLong(2, projectSeq);
      var rs = pst.executeUpdate();
      System.out.println("Project is assigned for " + rs + " employee.");
    }
  }

}

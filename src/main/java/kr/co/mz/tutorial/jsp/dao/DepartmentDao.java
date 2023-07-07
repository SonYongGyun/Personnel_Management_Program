package kr.co.mz.tutorial.jsp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import kr.co.mz.tutorial.jsp.db.HikariPoolFactory;
import kr.co.mz.tutorial.jsp.db.QueryManager;
import kr.co.mz.tutorial.jsp.dto.DepartmentDto;

public class DepartmentDao {

  public DataSource dataSource;

  public DepartmentDao() {
    dataSource = new HikariPoolFactory().createHikariDataSource();
  }

  public List<DepartmentDto> findAll() throws SQLException {
    try (var conn = dataSource.getConnection()) {
      var pst = conn.prepareStatement(QueryManager.getQuery("SELECT_ALL_DEPARTMENT"));
      var rs = pst.executeQuery();
      var departmentList = new ArrayList<DepartmentDto>();
      while (rs.next()) {
        var departmentDto = new DepartmentDto().fromResultSet(rs);
        departmentList.add(departmentDto);
      }

    }
    return null;
  }

  public Optional<DepartmentDto> findOneBySeq(long seq) throws SQLException {
    return Optional.empty();
  }

  public Optional<DepartmentDto> findOneByDepartmentName(String departmentName) throws SQLException {
    return Optional.empty();
  }

  public Optional<Integer> insertOne(DepartmentDto departmentDto) throws SQLException {
    return Optional.empty();
  }
}

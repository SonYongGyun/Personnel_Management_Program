package kr.co.mz.tutorial.jsp.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import kr.co.mz.tutorial.jsp.dto.DepartmentDto;

public class DepartmentDao {

  public List<DepartmentDto> findAll() throws SQLException {
    return null;
  }

  public Optional<DepartmentDto> findOneBySeq(long seq) throws SQLException {
    return Optional.empty();
  }

  
}

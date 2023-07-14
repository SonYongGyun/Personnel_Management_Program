package kr.co.mz.tutorial.service;

import static kr.co.mz.tutorial.Constants.HIKARI_DATASOURCE_CONTEXT_KEY;
import static kr.co.mz.tutorial.Constants.HOME_REDIRECT_URL;
import static kr.co.mz.tutorial.Constants.QUERY_MANAGER_CONTEXT_KEY;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContext;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import kr.co.mz.tutorial.dao.DepartmentDao;
import kr.co.mz.tutorial.db.QueryManager;
import kr.co.mz.tutorial.dto.DepartmentDto;
import kr.co.mz.tutorial.exception.DataBaseException;
import kr.co.mz.tutorial.exception.DataBaseInsertException;
import kr.co.mz.tutorial.exception.WrongAccessException;

public class DepartmentService {

  private final HikariDataSource hikariDataSource;
  private final QueryManager queryManager;

  public DepartmentService(ServletContext servletContext) {
    this.hikariDataSource = (HikariDataSource) servletContext.getAttribute(HIKARI_DATASOURCE_CONTEXT_KEY);
    this.queryManager = (QueryManager) servletContext.getAttribute(QUERY_MANAGER_CONTEXT_KEY);
  }

  public List<DepartmentDto> findAll() {
    try (
        var conn = hikariDataSource.getConnection();
    ) {
      var dao = new DepartmentDao(conn, queryManager);
      return dao.findAll();
    } catch (SQLException sqle) {
      throw new DataBaseException("데이터베이스 커넥션 오류가 났습니다: " + sqle.getMessage(), sqle);
    }
  }

  public Optional<DepartmentDto> findOneByKey(String filteredKey, String howToFind) {
    try (
        var conn = hikariDataSource.getConnection();
    ) {
      var departmentDao = new DepartmentDao(conn, queryManager);

      if (howToFind.equals("byName")) {
        return departmentDao.findOneByDepartmentName(filteredKey);
      } else if (howToFind.equals("bySeq")) {
        return departmentDao.findOneBySeq(Long.parseLong(filteredKey));
      }

      throw new WrongAccessException("잘못된 접근입니다.", HOME_REDIRECT_URL);
    } catch (SQLException sqle) {
      throw new DataBaseException("데이터베이스 관련 문제가 발생했습니다: " + sqle.getMessage(), sqle);
    }
  }

  public int insert(DepartmentDto departmentDto) {
    try (
        var conn = hikariDataSource.getConnection()
    ) {
      var departmentDao = new DepartmentDao(conn, queryManager);


    } catch (SQLException sqle) {
      sqle.printStackTrace();
      throw new DataBaseInsertException("Department 삽입에 실패했습니다.", sqle);
    }
  }
}

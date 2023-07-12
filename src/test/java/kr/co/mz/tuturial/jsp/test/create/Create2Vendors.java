package kr.co.mz.tuturial.jsp.test.create;

import java.io.IOException;
import java.sql.SQLException;
import kr.co.mz.tutorial.DataBaseException;
import kr.co.mz.tutorial.dao.VendorDao;
import kr.co.mz.tutorial.db.HikariPoolFactory;
import kr.co.mz.tutorial.db.QueryManager;
import kr.co.mz.tutorial.dto.VendorDto;

public class Create2Vendors {

  public static void main(String[] args) throws IOException, SQLException {
    // 부서 3개, 프로젝트 3개, 직원 5명, 협력사 2개.
    var ds = new HikariPoolFactory().createHikariDataSource();
    var qm = new QueryManager();
    try {
      var vendorDao = new VendorDao(ds.getConnection(), qm);

      var vendorDto1 = new VendorDto();
      vendorDto1.setVendorName("Vendor1");
      vendorDto1.setContact("010-asdf-xcvb");
      vendorDto1.setCreatedBy("admin");

      var vendorDto2 = new VendorDto();
      vendorDto2.setVendorName("Vendor2");
      vendorDto2.setContact("010-arrf-fghb");
      vendorDto2.setCreatedBy("admin");

      vendorDao.insertOne(vendorDto1);
      vendorDao.insertOne(vendorDto2);
    } catch (SQLException e) {
      throw new DataBaseException("데이터베이스 문제가 발생했습니다." + e.getMessage(), e);
    }

  }
}

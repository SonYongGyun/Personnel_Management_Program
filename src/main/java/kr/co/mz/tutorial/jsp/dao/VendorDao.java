package kr.co.mz.tutorial.jsp.dao;

import java.sql.SQLException;
import javax.sql.DataSource;
import kr.co.mz.tutorial.jsp.db.QueryManager;
import kr.co.mz.tutorial.jsp.dto.VendorDto;

public class VendorDao {

  public DataSource dataSource;

  public VendorDao(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void insertOne(VendorDto vendorDto) {
    try (
        var conn = dataSource.getConnection();
        var pst = conn.prepareStatement(QueryManager.getQuery("INSERT_VENDOR"))
    ) {
      pst.setString(1, vendorDto.getVendorName());
      pst.setString(2, vendorDto.getContact());
      pst.setString(3, vendorDto.getCreatedBy());
      var rs = pst.executeUpdate();
      System.out.println("Insert into vendor complete for " + rs + " rows.");
    } catch (SQLException sqle) {
      System.out.println("Failed to insert." + sqle.getMessage());
      sqle.printStackTrace();
    }


  }
}

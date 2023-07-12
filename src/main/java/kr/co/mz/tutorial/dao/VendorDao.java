package kr.co.mz.tutorial.dao;

import java.sql.Connection;
import java.sql.SQLException;
import kr.co.mz.tutorial.db.QueryManager;
import kr.co.mz.tutorial.dto.VendorDto;

public class VendorDao extends AbstractDao {

  private Connection conn;
  private final QueryManager queryManager;

  public VendorDao(Connection conn, QueryManager queryManager) {
    this.conn = conn;
    this.queryManager = queryManager;
  }

  public void insertOne(VendorDto vendorDto) {
    try (
        var pst = conn.prepareStatement(queryManager.getQuery("INSERT_VENDOR"))
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

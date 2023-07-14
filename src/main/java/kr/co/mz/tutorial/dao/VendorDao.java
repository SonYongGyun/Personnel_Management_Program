package kr.co.mz.tutorial.dao;

import java.sql.Connection;
import java.sql.SQLException;
import kr.co.mz.tutorial.db.QueryManager;
import kr.co.mz.tutorial.dto.VendorDto;
import kr.co.mz.tutorial.exception.DataBaseInsertException;

public class VendorDao {

  private Connection conn;
  private final QueryManager queryManager;

  public VendorDao(Connection conn, QueryManager queryManager) {
    this.conn = conn;
    this.queryManager = queryManager;
  }

  public void insertOne(VendorDto vendorDto) throws DataBaseInsertException {
    try (
        var pst = conn.prepareStatement(queryManager.getQuery("INSERT_VENDOR"))
    ) {
      pst.setString(1, vendorDto.vendorName());
      pst.setString(2, vendorDto.contact());
      pst.setString(3, vendorDto.createdBy());
      var rs = pst.executeUpdate();
      System.out.println("Insert into vendor complete for " + rs + " rows.");
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      throw new DataBaseInsertException("Failed to insert." + sqle.getMessage(), "", sqle);
    }


  }
}

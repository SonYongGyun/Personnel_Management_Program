package kr.co.mz.tuturial.jsp.test;

import kr.co.mz.tutorial.jsp.dto.VendorDto;

public class CreateVendorDto {

  public VendorDto nameCloud() {
    var dto = new VendorDto();
    dto.setVendorName("Cloud");
    dto.setContact("010-3333-3333");
    dto.setCreatedBy("admin");
    return dto;
  }

  public VendorDto nameAOA() {
    var dto = new VendorDto();
    dto.setVendorName("AOA");
    dto.setContact("010-1111-1111");
    dto.setCreatedBy("admin");
    return dto;
  }
}

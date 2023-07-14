package kr.co.mz.tuturial.jsp.test.create;

import kr.co.mz.tutorial.dto.VendorDto;

public class CreateVendorDto {

  public VendorDto nameCloud() {
    var dto = new VendorDto("Cloud", "010-3333-3333", "admin");
    return dto;
  }

  public VendorDto nameAOA() {
    var dto = new VendorDto("AOA", "010-1111-1111", "admin");
    return dto;
  }
}

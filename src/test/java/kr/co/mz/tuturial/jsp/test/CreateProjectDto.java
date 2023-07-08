package kr.co.mz.tuturial.jsp.test;

import java.math.BigDecimal;
import kr.co.mz.tutorial.jsp.dto.ProjectDto;

public class CreateProjectDto {

  public ProjectDto nameMegaBird() {
    var dto = new ProjectDto();
    dto.setProjectName("MegaBird");
    dto.setProjectDescription("Corp.MagaZone's Message Service.");
    dto.setProjectStatus("운영중");
    dto.setBudget(BigDecimal.valueOf(1000000000));
    dto.setCreatedBy("admin");
    return dto;
  }

  public ProjectDto nameDocsWave() {
    var dto = new ProjectDto();
    dto.setProjectName("DocsWave");
    dto.setProjectDescription("Corp.MagaZone's Document Service(?).");
    dto.setProjectStatus("운영중");
    dto.setBudget(BigDecimal.valueOf(1000000000));
    dto.setCreatedBy("admin");
    return dto;
  }

  public ProjectDto nameAOAI_DA() {
    var dto = new ProjectDto();
    dto.setProjectName("AOAI_DA");
    dto.setProjectDescription("Corp.MagaZone's Document analysis with AOAI Service(?).");
    dto.setProjectStatus("사업계획중");
    dto.setBudget(BigDecimal.valueOf(100));
    dto.setCreatedBy("admin");
    return dto;
  }

}

package kr.co.mz.tutorial.dao;

import kr.co.mz.tutorial.db.QueryManager;

public abstract class AbstractDao {

  protected QueryManager queryManager;

  public AbstractDao() {
    //중복코드 제거, 트랜잭션 유연성, 확장성, 테스트용이성
  }

  public void setQueryManager(QueryManager queryManager) {
    this.queryManager = queryManager;
  }


}

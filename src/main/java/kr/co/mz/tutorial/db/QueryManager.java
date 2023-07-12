package kr.co.mz.tutorial.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public class QueryManager implements Serializable {

  public static final long serialVersionUID = 235982534L;
  private static final String QUERY_FILE = "db/queries.properties";
  private final Properties properties;

  {//객체를 초기화 하는 방법.
    properties = new Properties();
    InputStream inputStream;
    try {
      inputStream = QueryManager.class.getClassLoader().getResourceAsStream(QUERY_FILE);
      properties.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getQuery(String key) {
    return properties.getProperty(key);
  }

}

/*
정적 블록: 클래스가 로드될 때 한 번만 실행되어야 하기 때문
 프로그램 실행 중에 한 번만 초기화, 계속해서 사용
 다른 클래스에서도 접근할 수 있음
* */
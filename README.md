# 서버의 MySQL 정보(외부에서도 접속이 가능)

    datasource:
      url: jdbc:mysql://127.0.0.1:3307/kkangtongs_funfact?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8&allowPublicKeyRetrieval=true&useSSL=false
      username: admin
      password: gachon
      driver-class-name: com.mysql.cj.jdbc.Driver

# 기본 이미지를 사용합니다.
FROM openjdk:17-jdk-slim

# 컨테이너 내에서 애플리케이션을 실행할 작업 디렉토리를 설정합니다.
WORKDIR /app

# 로컬 머신에서 빌드한 Eureka 서비스 JAR 파일을 컨테이너 내로 복사합니다.
COPY build/libs/eureka-server-0.0.1-SNAPSHOT.jar /app/eureka-server.jar

# Eureka 서비스 애플리케이션을 실행합니다.
CMD ["java", "-jar", "-Dserver.port=8761", "eureka-server.jar"]

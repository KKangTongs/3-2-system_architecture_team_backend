# 기본 이미지를 사용합니다.
FROM openjdk:17-jre-slim

# 컨테이너 내에서 애플리케이션을 실행할 작업 디렉토리를 설정합니다.
WORKDIR /app

# 로컬 머신에서 빌드한 게이트웨이 JAR 파일을 컨테이너 내로 복사합니다.
COPY build/libs/gateway-0.0.1-SNAPSHOT.jar /app/gateway.jar

# 게이트웨이 애플리케이션을 실행합니다.
CMD ["java", "-jar", "-Dserver.port=8000", "gateway.jar"]

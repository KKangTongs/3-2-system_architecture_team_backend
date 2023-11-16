killall java

cd eureka-server/
./gradlew build
nohup java -jar build/libs/eureka-server-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &

cd ../gateway/
./gradlew build
nohup java -jar build/libs/gateway-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &

cd ../auth-service/
./gradlew build
nohup java -jar build/libs/auth-service-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &

cd ../board-service/
./gradlew build
nohup java -jar build/libs/board-service-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &

cd ../ws-server/
./gradlew build
nohup java -jar build/libs/ws-server-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &
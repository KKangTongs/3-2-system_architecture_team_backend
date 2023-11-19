pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Stop and Remove All Containers') {
            steps {
                script {
                    try {
                        // 모든 컨테이너 중지
                        sh 'docker stop $(docker ps -a -q)'
                    } catch (Exception ex) {
                    }
                    try {
                        // 모든 컨테이너 제거
                        sh 'docker rm $(docker ps -a -q)'
                    } catch (Exception ex) {
                    }
                }
            }
        }

        stage('Build and Deploy eureka-server') {
            steps {
                dir('eureka-server') {
                    sh './gradlew clean build'
                    // Docker 이미지 빌드 및 푸시
                    sh 'docker build -t ideawolf/eureka-server .'
                    sh 'docker run -d -p 8761:8761 --name eureka-server --network msa-network ideawolf/eureka-server'
                }
            }
        }

        stage('Build and Deploy gateway') {
            steps {
                dir('gateway') {
                    sh './gradlew clean build'
                    // Docker 이미지 빌드 및 푸시
                    sh 'docker build -t ideawolf/gateway .'
                    sh 'docker run -d -p 8000:8000 --name gateway --network msa-network ideawolf/gateway'
                }
            }
        }

        stage('Build and Deploy funfact-service') {
            steps {
                dir('funfact-service') {
                    sh './gradlew clean build'
                    // Docker 이미지 빌드 및 푸시
                    sh 'docker build -t ideawolf/funfact-service .'
                    sh 'docker run -d -p 8002:8002 --name funfact-service --network msa-network ideawolf/funfact-service'
                }
            }
        }

        stage('Build and Deploy auth-service') {
            steps {
                dir('auth-service') {
                    sh './gradlew clean build'
                    // Docker 이미지 빌드 및 푸시
                    sh 'docker build -t ideawolf/auth-service .'
                    sh 'docker run -d -p 8001:8001 --name auth-service --network msa-network ideawolf/auth-service'
                }
            }
        }
    }

    post {
        always {
            // 항상 실행할 단계 예시
            echo '빌드 프로세스가 완료되었습니다.'
            // 여기에 추가적인 로그 수집, 알림 전송 등의 단계를 포함할 수 있습니다.
        }
    }
}

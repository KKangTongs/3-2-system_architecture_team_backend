pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Deploy eureka-server') {
            steps {
                dir('eureka-server') {
                    sh './gradlew clean build'
                    // Docker 이미지 빌드 및 푸시
                    sh 'docker build -t ideawolf/eureka-server .'
//                     sh 'docker push ideawolf/eureka-server'
                }
            }
        }

        stage('Build and Deploy gateway') {
            steps {
                dir('gateway') {
                    sh './gradlew clean build'
                    // Docker 이미지 빌드 및 푸시
                    sh 'docker build -t ideawolf/gateway .'
//                     sh 'docker push ideawolf/gateway'
                }
            }
        }

        stage('Build and Deploy funfact-service') {
            steps {
                dir('funfact-service') {
                    sh './gradlew clean build'
                    // Docker 이미지 빌드 및 푸시
                    sh 'docker build -t ideawolf/funfact-service .'
//                     sh 'docker push ideawolf/funfact-service'
                }
            }
        }

        stage('Build and Deploy auth-service') {
            steps {
                dir('auth-service') {
                    sh './gradlew clean build'
                    // Docker 이미지 빌드 및 푸시
                    sh 'docker build -t ideawolf/auth-service .'
//                     sh 'docker push ideawolf/auth-service'
                }
            }
        }


        stage('Deploy All Services') {
            steps {
                script {
                    sh './deploy.sh'
                }
            }
        }



        stage('Deploy Eureka Server') {
            steps {
                sh 'docker run -d -p 8761:8761 --name eureka-server --network msa-network ideawolf/eureka-server'
            }
        }

        stage('Deploy auth-service') {
            steps {
                sh 'docker run -d -p 8001:8001 --name auth-service --network msa-network ideawolf/auth-service'
            }
        }

        stage('Deploy funfact-service') {
            steps {
                sh 'docker run -d -p 8002:8002 --name funfact-service --network msa-network ideawolf/funfact-service'
            }
        }

        stage('Deploy gateway') {
            steps {
                sh 'docker run -d -p 8000:8000 --name gateway --network msa-network ideawolf/gateway'
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

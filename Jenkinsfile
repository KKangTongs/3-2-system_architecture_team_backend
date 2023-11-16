pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test auth-service') {
            steps {
                dir('auth-service') {
                    sh './gradlew clean build'
                }
            }
        }

        stage('Build and Test eureka-server') {
            steps {
                dir('eureka-server') {
                    sh './gradlew clean build'
                }
            }
        }

        stage('Build and Test funfact-service') {
            steps {
                dir('funfact-service') {
                    sh './gradlew clean build'
                }
            }
        }

        stage('Build and Test gateway') {
            steps {
                dir('gateway') {
                    sh './gradlew clean build'
                }
            }
        }

        stage('Build and Test ws-server') {
            steps {
                dir('ws-server') {
                    sh './gradlew clean build'
                }
            }
        }

//         // 추가 단계: 배포, 알림 등
//         stage('Build and Deploy auth-service') {
//             steps {
//                 dir('auth-service') {
//                     sh './gradlew clean build'
//                     // Docker 이미지 빌드 및 푸시
//                     sh 'docker build -t auth-service .'
//                     sh 'docker push your-docker-registry/auth-service'
//                     // 배포 스크립트 실행
//                     sh 'deploy-scripts/deploy-auth-service.sh'
//                 }
//             }
//         }


    }

    post {
        always {
            // 로그 수집, 결과 알림 보내기 등
        }
    }
}

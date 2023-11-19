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
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        def eurekaImage = docker.build('ideawolf/eureka-server')
                        eurekaImage.push()
                    }
                }
            }
        }

        stage('Build and Deploy gateway') {
            steps {
                dir('gateway') {
                    sh './gradlew clean build'
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        def gatewayImage = docker.build('ideawolf/gateway')
                        gatewayImage.push()
                    }
                }
            }
        }

        stage('Build and Deploy funfact-service') {
            steps {
                dir('funfact-service') {
                    sh './gradlew clean build'
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        def funfactServiceImage = docker.build('ideawolf/funfact-service')
                        funfactServiceImage.push()
                    }
                }
            }
        }

        stage('Build and Deploy auth-service') {
            steps {
                dir('auth-service') {
                    sh './gradlew clean build'
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        def authServiceImage = docker.build('ideawolf/auth-service')
                        authServiceImage.push()
                    }
                }
            }
        }

        stage('Deploy All Services') {
            steps {
                script {
                    sh 'deploy.sh'
                }
            }
        }

    }

    post {
        always {
            echo '빌드 프로세스가 완료되었습니다.'
        }
    }
}

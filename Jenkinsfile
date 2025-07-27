pipeline {
    agent any

    tools {
        maven 'Maven 3.5.1'
        jdk 'Java 21'
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-creds')
        DOCKERHUB_USERNAME = 'your-dockerhub-username'
    }

    stages {

        stage('Build and Push vehicle-service') {
            steps {
                dir('vehicle-service') {
                    sh 'mvn clean install'
                    sh 'docker build -t $DOCKERHUB_USERNAME/vehicle-service:latest .'
                    sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                    sh 'docker push $DOCKERHUB_USERNAME/vehicle-service:latest'
                }
                sh 'kubectl apply -f deployment/vehicle-service-deployment.yaml'
            }
        }

        stage('Build and Push tracking-service') {
            steps {
                dir('tracking-service') {
                    sh 'mvn clean install'
                    sh 'docker build -t $DOCKERHUB_USERNAME/tracking-service:latest .'
                    sh 'docker push $DOCKERHUB_USERNAME/tracking-service:latest'
                }
                sh 'kubectl apply -f deployment/tracking-service-deployment.yaml'
            }
        }

        stage('Build and Push dashboard-service') {
            steps {
                dir('dashboard-service') {
                    sh 'mvn clean install'
                    sh 'docker build -t $DOCKERHUB_USERNAME/dashboard-service:latest .'
                    sh 'docker push $DOCKERHUB_USERNAME/dashboard-service:latest'
                }
                sh 'kubectl apply -f deployment/dashboard-service-deployment.yaml'
            }
        }

        stage('Build and Push gateway-service') {
            steps {
                dir('gateway-service') {
                    sh 'mvn clean install'
                    sh 'docker build -t $DOCKERHUB_USERNAME/gateway-service:latest .'
                    sh 'docker push $DOCKERHUB_USERNAME/gateway-service:latest'
                }
                sh 'kubectl apply -f deployment/gateway-service-deployment.yaml'
            }
        }

        stage('Build and Push service-registry') {
            steps {
                dir('service-registry') {
                    sh 'mvn clean install'
                    sh 'docker build -t $DOCKERHUB_USERNAME/service-registry:latest .'
                    sh 'docker push $DOCKERHUB_USERNAME/service-registry:latest'
                }
                sh 'kubectl apply -f deployment/service-registry-deployment.yaml'
            }
        }
    }

    post {
        success {
            echo '✅ Bhaukal build & deploy done!'
        }
        failure {
            echo '❌ Arre bhai, kuch to error hua hai! Check logs.'
        }
    }
}

pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-creds')
    }

    stages {
        stage('Build and Push vehicle-service') {
            steps {
                dir('vehicle-service') {
                    bat 'mvn clean install'
                    bat 'echo %DOCKERHUB_CREDENTIALS_PSW% | docker login -u %DOCKERHUB_CREDENTIALS_USR% --password-stdin'
                    bat 'docker build -t %DOCKERHUB_CREDENTIALS_USR%/vehicle-service:latest .'
                    bat 'docker push %DOCKERHUB_CREDENTIALS_USR%/vehicle-service:latest'
                }
                withCredentials([file(credentialsId: 'kubeconfig-file', variable: 'KUBECONFIG')]) {
                    bat 'kubectl apply -f vehicle-service/deployment/vehicle-service-deployment.yaml'
                }
            }
        }

        stage('Build and Push tracking-service') {
            steps {
                dir('tracking-service') {
                    bat 'mvn clean install'
                    bat 'docker build -t %DOCKERHUB_CREDENTIALS_USR%/tracking-service:latest .'
                    bat 'docker push %DOCKERHUB_CREDENTIALS_USR%/tracking-service:latest'
                }
                withCredentials([file(credentialsId: 'kubeconfig-file', variable: 'KUBECONFIG')]) {
                    bat 'kubectl apply -f tracking-service/deployment/tracking-service-deployment.yaml'
                }
            }
        }

        stage('Build and Push dashboard-service') {
            steps {
                dir('dashboard-service') {
                    bat 'mvn clean install'
                    bat 'docker build -t %DOCKERHUB_CREDENTIALS_USR%/dashboard-service:latest .'
                    bat 'docker push %DOCKERHUB_CREDENTIALS_USR%/dashboard-service:latest'
                }
                withCredentials([file(credentialsId: 'kubeconfig-file', variable: 'KUBECONFIG')]) {
                    bat 'kubectl apply -f dashboard-service/deployment/dashboard-service-deployment.yaml'
                }
            }
        }

        stage('Build and Push gateway-service') {
            steps {
                dir('gateway-service') {
                    bat 'mvn clean install'
                    bat 'docker build -t %DOCKERHUB_CREDENTIALS_USR%/gateway-service:latest .'
                    bat 'docker push %DOCKERHUB_CREDENTIALS_USR%/gateway-service:latest'
                }
                withCredentials([file(credentialsId: 'kubeconfig-file', variable: 'KUBECONFIG')]) {
                    bat 'kubectl apply -f gateway-service/deployment/gateway-service-deployment.yaml'
                }
            }
        }

        stage('Build and Push service-registry') {
            steps {
                dir('service-registry') {
                    bat 'mvn clean install'
                    bat 'docker build -t %DOCKERHUB_CREDENTIALS_USR%/service-registry:latest .'
                    bat 'docker push %DOCKERHUB_CREDENTIALS_USR%/service-registry:latest'
                }
                withCredentials([file(credentialsId: 'kubeconfig-file', variable: 'KUBECONFIG')]) {
                    bat 'kubectl apply -f service-registry/deployment/service-registry-deployment.yaml'
                }
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

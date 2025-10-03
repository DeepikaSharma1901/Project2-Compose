pipeline {
    agent { 
        docker { image 'node:16' } 
    }

    stages {
        stage('Install Dependencies') {
            steps {
                sh 'npm install'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'npm test'
            }
        }

        stage('Security Scan') {
            steps {
                sh 'npm install -g snyk'
                sh 'snyk test || exit 1'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t 21952195/nodeapp:latest .'
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([string(credentialsId: 'dockerhub-pass', variable: 'DOCKER_PASS')]) {
                    sh 'echo $DOCKER_PASS | docker login -u 21952195 --password-stdin'
                    sh 'docker push 21952195/nodeapp:latest'
                }
            }
        }
    }
}

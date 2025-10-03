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
                sh 'docker build -t deepikasharma1901/nodeapp:latest .'
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([string(credentialsId: 'dockerhub-pass', variable: 'DOCKER_PASS')]) {
                    sh 'echo $DOCKER_PASS | docker login -u deepikasharma1901 --password-stdin'
                    sh 'docker push deepikasharma1901/nodeapp:latest'
                }
            }
        }
    }
}

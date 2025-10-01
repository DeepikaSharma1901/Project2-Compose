pipeline {
  agent any
  environment {
    IMAGE_NAME = "express-sample"   // change if your app/image name is different
  }
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }
    stage('Build & Push Docker image') {
      steps {
        script {
          withCredentials([usernamePassword(
              credentialsId: 'dockerhub-creds',
              usernameVariable: 'DOCKERHUB_USER',
              passwordVariable: 'DOCKERHUB_TOKEN'
          )]) {
            sh '''
              echo "$DOCKERHUB_TOKEN" | docker login -u "$DOCKERHUB_USER" --password-stdin
              docker build -t $DOCKERHUB_USER/${IMAGE_NAME}:$BUILD_NUMBER -t $DOCKERHUB_USER/${IMAGE_NAME}:latest .
              docker push $DOCKERHUB_USER/${IMAGE_NAME}:$BUILD_NUMBER
              docker push $DOCKERHUB_USER/${IMAGE_NAME}:latest
              docker logout
            '''
          }
        }
      }
    }
  }
  post {
    always { sh 'docker image prune -f || true' }
  }
}

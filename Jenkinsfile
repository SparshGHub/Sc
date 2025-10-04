pipeline {
  agent any
  options {
    timestamps()
    timeout(time: 60, unit: 'MINUTES')
  }

  environment {
    DOCKER_IMAGE = "sparshdockerman/scicalc"
    CREDENTIALS_ID = "dockerHubCreds"
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Verify Tooling') {
      steps {
        sh 'java -version || true'
        sh 'mvn -v || true'
        sh 'docker --version || true'
        sh 'ansible --version || true'
      }
    }

stage('Test') {
  steps {
    dir('SciCalC/thescicalc') {
      sh 'mvn -B -q test'
    }
  }
  post {
    always {
      junit 'SciCalC/thescicalc/target/surefire-reports/*.xml'
    }
  }
}

stage('Package') {
  steps {
    dir('SciCalC/thescicalc') {
      sh 'mvn -B -q -DskipTests package'
    }
  }
  post {
    success {
      archiveArtifacts artifacts: 'SciCalC/thescicalc/target/*.jar', fingerprint: true
    }
  }
}

stage('Docker Build & Push') {
  steps {
    script {
      def sha = env.GIT_COMMIT ?: sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
      def imgLatest = "${DOCKER_IMAGE}:latest"
      def imgSha = "${DOCKER_IMAGE}:${sha}"
      sh "docker build -f SciCalC/thescicalc/Dockerfile -t ${imgLatest} -t ${imgSha} SciCalC/thescicalc"

      withCredentials([usernamePassword(credentialsId: env.CREDENTIALS_ID,
                                        usernameVariable: 'DOCKERHUB_USER',
                                        passwordVariable: 'DOCKERHUB_TOKEN')]) {
        sh '''
          echo "$DOCKERHUB_TOKEN" | docker login -u "$DOCKERHUB_USER" --password-stdin
          docker push ${DOCKER_IMAGE}:latest
          docker logout
        '''
      }
    }
  }
}


  post {
    success {
      echo 'Pipeline completed successfully!'
    }
    failure {
      echo 'Pipeline failed!'
    }
    always {
      echo 'CI/CD pipeline finished.'
    }
  }
}
}

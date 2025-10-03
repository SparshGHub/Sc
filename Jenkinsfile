pipeline {
  agent any
  options { timestamps(); timeout(time: 60, unit: 'MINUTES') }

  environment {
    APP_DIR = 'SciCalC/thescicalc'
    DOCKER_IMAGE = 'sparshdockerman/scicalc'
    CREDENTIALS_ID = 'dockerHubCreds'
  }

  stages {
    stage('Checkout') { steps { checkout scm } }

    stage('Verify Tooling') {
      steps {
        sh 'java -version || true'
        sh 'mvn -v || true'
        sh 'docker --version || true'
      }
    }
    
    stage('Test') {
      steps {
        dir(env.APP_DIR) {
          sh 'mvn -B -q test'
        }
      }
      post {
        always {
          junit "${env.APP_DIR}/**/target/surefire-reports/*.xml"
        }
      }
    }

    stage('Package') {
      steps {
        dir(env.APP_DIR) {
          sh 'mvn -B -q -DskipTests package'
        }
      }
      post {
        success {
          archiveArtifacts artifacts: "${env.APP_DIR}/target/*.jar", fingerprint: true
        }
      }
    }

    stage('Docker Build') {
      steps {
        script {
          def sha = env.GIT_COMMIT ?: sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
          // Build using the subfolder as the Docker context; Dockerfile also lives there
          sh """
            docker build --pull \
              -f ${env.APP_DIR}/Dockerfile \
              -t ${env.DOCKER_IMAGE}:latest \
              -t ${env.DOCKER_IMAGE}:${sha} \
              ${env.APP_DIR}
          """
        }
      }
    }

    stage('Docker Push (main only)') {
      when { expression { env.BRANCH_NAME == 'main' || env.GIT_BRANCH == 'origin/main' || env.GIT_BRANCH == 'main' } }
      steps {
        withCredentials([usernamePassword(credentialsId: env.CREDENTIALS_ID,
                                          usernameVariable: 'DOCKERHUB_USER',
                                          passwordVariable: 'DOCKERHUB_TOKEN')]) {
          script {
            def sha = env.GIT_COMMIT ?: sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
            sh '''
              echo "$DOCKERHUB_TOKEN" | docker login -u "$DOCKERHUB_USER" --password-stdin
              docker push "${DOCKER_IMAGE}:latest"
              docker push "${DOCKER_IMAGE}:'${sha}'"
              docker logout
            '''
          }
        }
      }
    }
  }

  post { always { echo 'CI pipeline finished.' } }
}

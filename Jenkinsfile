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

    stage('Verify Tools') {
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
          sh 'mvn -B test'
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
          sh 'mvn -B package -DSkipTests'
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

    stage('Deploy with Ansible') {
      steps {
        dir('SciCalC/thescicalc/ansible') {
          withCredentials([usernamePassword(credentialsId: env.CREDENTIALS_ID,
                                            usernameVariable: 'DOCKERHUB_USER',
                                            passwordVariable: 'DOCKERHUB_TOKEN')]) {
            sh '''
              ansible-playbook -i inventory deploy.yml \
                --extra-vars "docker_image=${DOCKER_IMAGE}:latest docker_user=$DOCKERHUB_USER docker_token=$DOCKERHUB_TOKEN"
            '''
          }
        }
      }
    }
  }

  post {
    success {
      echo 'Build and deployment successful.'
    }
    failure {
      echo 'Build or deployment failed. Check console output.'
    }
    always {
      echo 'Pipeline completed.'
    }
  }
}

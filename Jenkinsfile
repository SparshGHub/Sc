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
        sh 'mvn -B -q test'
      }
      post {
        always {
          junit '**/target/surefire-reports/*.xml'
        }
      }
    }

    stage('Package') {
      steps {
        sh 'mvn -B -q -DskipTests package'
      }
      post {
        success {
          archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
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

    stage('Deploy via Ansible') {
      when {
        expression { env.BRANCH_NAME == 'main' || env.GIT_BRANCH == 'origin/main' || env.GIT_BRANCH == 'main' }
      }
      steps {
        echo 'Deploying using Ansible...'
        sh '''
          cd SciCalC/thescicalc
          ansible-playbook -i ansible/hosts.ini ansible/deploy.yml --extra-vars "ansible_become_pass=$SUDO_PASS"
        '''
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

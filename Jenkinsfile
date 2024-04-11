pipeline {
  agent any
  tools {
    maven "MAVEN3"
    jdk "OracleJDK17"
  }
  stages {
    stage ('Fetch Code') {
      steps {
        git branch: 'main', url: 'https://github.com/WikiCoding/domain_driven_design_todo_rest_api.git'
      }
    }

    stage ('Build code') {
      steps {
        sh 'mvn install -DskipTests'
      }

      post {
        success {
          echo 'Archiving artifacts now.'
          archiveArtifacts artifacts: '**/*.jar'
        }
      }
    }

    stage('Unit Tests') {
      steps {
        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
          sh 'mvn test'
        }
      }
    }

    stage('Checkstyle Analysis') {
      steps {
        sh 'mvn checkstyle:checkstyle'
      }
    }

    stage('Sonar Analysis') {
      environment {
        scannerHome = tool 'sonar4.7'
      }
      steps {
         withSonarQubeEnv('sonar') {
          sh '''${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=springtodorestapi \
          -Dsonar.projectName=springtodorestapi \
          -Dsonar.projectVersion=1.0 \
          -Dsonar.sources=src/ \
          -Dsonar.java.binaries=target/test-classes/** \
          -Dsonar.junit.reportsPath=target/surefire-reports/ \
          -Dsonar.jacoco.reportsPath=target/jacoco.exec \
          -Dsonar.java.checkstyle.reportPaths=target/checkstyle-result.xml'''
        }
      }
    }
  }
}
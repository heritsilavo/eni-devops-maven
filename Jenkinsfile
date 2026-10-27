
pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests compile'
            }
        }

        stage('Tests') {
            steps {
                sh 'mvn -B test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn -B -DskipTests package'
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true,
                  testResults: 'target/surefire-reports/*.xml'
        }

        success {
            archiveArtifacts artifacts: 'target/*.jar',
                             fingerprint: true
            echo 'Pipeline Maven termine avec succes !'
        }
    }
}


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

        
        stage('Docker Build') {
            steps {
                sh 'docker build -t eni-maven-app:1.0 .'
            }
        }

        stage('Docker Test') {
            steps {
                sh 'docker run --rm eni-maven-app:1.0'
            }
        }

        
        stage('Docker Push') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-credentials',
                        usernameVariable: 'DOCKERHUB_USER',
                        passwordVariable: 'DOCKERHUB_TOKEN'
                    )
                ]) {
                    sh '''
                        set -eu

                        IMAGE="$DOCKERHUB_USER/eni-maven-app:1.0"

                        docker tag eni-maven-app:1.0 "$IMAGE"

                        set +x
                        echo "$DOCKERHUB_TOKEN" | docker login \
                            -u "$DOCKERHUB_USER" \
                            --password-stdin

                        docker push "$IMAGE"
                        docker logout
                    '''
                }
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

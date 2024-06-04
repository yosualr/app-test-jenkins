pipeline {
    agent any
    tools {
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo 'Git Checkout Completed'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install'
                echo 'Build Completed'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat 'mvn clean verify sonar:sonar -Dsonar.projectKey=my_project_key -Dsonar.host.url=http://localhost:9000'
                    echo 'SonarQube Analysis Completed'
                }
            }
        }
        stage('Quality Gate') {
            steps {
                script {
                    def qg = waitForQualityGate()
                    if (qg.status != 'OK') {
                        error "Pipeline aborted due to quality gate failure: ${qg.status}"
                    }
                }
                echo 'Quality Gate Completed'
            }
        }
    }
    post {
        always {
            echo 'Pipeline completed'
        }
    }
}

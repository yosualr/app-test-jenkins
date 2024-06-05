pipeline {
    agent any
    tools {
        maven 'Maven'
    }

    stages {
        stage('Git Checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/yosualr/app-test-jenkins']])
                bat 'mvn clean install'
                echo 'Git Checkout Completed'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat 'mvn clean package'
                    bat ''' mvn clean verify sonar:sonar -Dsonar.projectKey=xmart -Dsonar.projectName='xmart' -Dsonar.host.url=http://localhost:9000 '''
                    echo 'SonarQube Analysis Completed'
                }
            }
        }
        stage("Quality Gate") {
            steps {
                waitForQualityGate abortPipeline: true
                echo 'Quality Gate Completed'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    bat 'docker build -t yosualr/xmart .'
                    echo 'Build Docker Image Completed'
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub-pw', variable: 'dockerhub-password')]) {
                        bat ''' docker login -u yosualr -p "%dockerhub-password%" '''
                    }
                    bat 'docker push yosualr/xmart'
                }
            }
        }

        stage ('Docker Run') {
            steps {
                script {
                    bat 'docker run -d --name xmart -p 8099:8080 yosualr/xmart'
                    echo 'Docker Run Completed'
                }
            }
        }

    }
    post {
        always {
            bat 'docker logout'
        }
    }
}
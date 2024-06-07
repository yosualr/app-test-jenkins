pipeline {
    agent any
    tools {
        maven 'Maven'
    }

    environment {
        BUILD_NUMBER_ENV = "${env.BUILD_NUMBER}"
        TEXT_SUCCESS_BUILD = "[#${env.BUILD_NUMBER}] Project Name : ${JOB_NAME} is Success"
        TEXT_FAILURE_BUILD = "[#${env.BUILD_NUMBER}] Project Name : ${JOB_NAME} is Failure"
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
           success {
               script{
                    withCredentials([string(credentialsId: 'telegram-bot-token', variable: 'TOKEN'), string(credentialsId: 'telegram-chat-id-bot', variable: 'CHAT_ID')]) {
                       bat ''' curl -s -X POST https://api.telegram.org/bot"%TOKEN%"/sendMessage -d chat_id="%CHAT_ID%" -d text="%TEXT_SUCCESS_BUILD%" '''
                    }
               }
           }
           failure {
               script{
                   withCredentials([string(credentialsId: 'telegram-bot-token', variable: 'TOKEN'), string(credentialsId: 'telegram-chat-id-bot', variable: 'CHAT_ID')]) {
                       bat ''' curl -s -X POST https://api.telegram.org/bot"%TOKEN%"/sendMessage -d chat_id="%CHAT_ID%" -d text="%TEXT_FAILURE_BUILD%" '''
                   }
               }
           }
       }
}
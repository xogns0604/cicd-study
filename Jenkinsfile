pipeline {
    agent any
    
    triggers {
        githubPush()  // GitHub webhook 트리거
    }
    
    tools {
        jdk 'JDK17'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scmGit(
                    branches: [[name: 'main']],
                    userRemoteConfigs: [[
                        credentialsId: 'github-credential',
                        url: 'https://github.com/your-username/your-repo.git'
                    ]]
                )
            }
        }
        
        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }
        
        stage('Test') {
            steps {
                sh './gradlew test'
            }
            post {
                always {
                    junit '**/build/test-results/test/*.xml'
                }
            }
        }
        
        stage('Deploy') {
            steps {
                // 배포 스크립트
                sh '''
                    ./deploy.sh
                '''
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}

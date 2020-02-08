pipeline {
    agent none
    stages {
        stage('Clean workspace') {
            agent {
                docker {
                    image 'maven:3.6.3-jdk-8-slim'
                }
            }
            steps {
                sh 'mvn clean'
                sh 'mv src/main/resources/application_example.yml src/main/resources/application.yml'
                sh 'sed -i "s/password:.*/password: ${mappwd}/" src/main/resources/application.yml'
            }
        }
        stage('Build application') {
            agent {
                docker {
                    image 'maven:3.6.3-jdk-8-slim'
                }
            }
            steps {
                sh 'mvn install'
            }
        }
        stage('Build docker image') {
            agent any
            steps {
                sh 'docker image rm miraclewisp/hperproteinaemia-qa || true'
                sh 'docker build -t miraclewisp/hperproteinaemia-qa:${BUILD_NUMBER} -t miraclewisp/hperproteinaemia-qa:latest .'
            }

        }
        stage('Push docker image') {
            agent any
            steps {
                withDockerRegistry([credentialsId: "dockerhub", url: ""]) {
                    sh 'docker push miraclewisp/hperproteinaemia-qa:${BUILD_NUMBER}'
                    sh 'docker push miraclewisp/hperproteinaemia-qa:latest'
                }
            }

        }
        stage('Deploy') {
            agent any
            steps {
                sh 'ssh Rinslet docker stop qa || true'
                sh 'ssh Rinslet docker image rm miraclewisp/hperproteinaemia-qa || true'
                sh 'ssh Rinslet docker pull miraclewisp/hperproteinaemia-qa'
                sh 'ssh Rinslet docker run --rm --name qa -d -p 8083:8083 miraclewisp/hperproteinaemia-qa'
            }
        }
    }
}
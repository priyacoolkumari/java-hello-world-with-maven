pipeline {
    agent any
tools{
maven 'Maven'
}


    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_PROJECT_KEY = 'priyacoolkumari_java-hello-world-with-maven_fbd14a2b-1fee-4680-a5ec-72f43255788e'
        SONAR_TOKEN = credentials('sonar-token')  // Fetches the token securely from Jenkins credentials
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM',
                    branches: [[name: '*/master']],
                    extensions: [],
                    userRemoteConfigs: [[credentialsId: 'github access', url: 'https://github.com/sreenivas449/java-hello-world-with-maven.git']]
                ])
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def mvnHome = tool 'maven'  // Ensure this matches the configured tool in Jenkins
                    withSonarQubeEnv('SonarQube') {  // 'SonarQube' must match Jenkins SonarQube config name
                        sh "${mvnHome}/bin/mvn clean verify sonar:sonar \
                            -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                            -Dsonar.projectName='java-hello-world-with-maven' \
                            -Dsonar.host.url=${SONAR_HOST_URL} \
                            -Dsonar.login=${SONAR_TOKEN} \
                            -Dsonar.sourceEncoding=UTF-8"
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    timeout(time: 1, unit: 'MINUTES') {
                        def qualityGate = waitForQualityGate()
                        if (qualityGate.status != 'OK') {
                            error "Pipeline failed due to quality gate failure: ${qualityGate.status}"
                        }
                    }
                }
            }
        }
    }
}

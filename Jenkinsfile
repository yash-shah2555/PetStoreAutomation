pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'   // configure Maven in Jenkins → Global Tool Configuration
        jdk 'JAVA_HOME'      // configure JDK in Jenkins → Global Tool Configuration
    }

    stages {
        stage('Checkout') {
            steps {
                // Pull code from GitHub
                git branch: 'main', url: 'https://github.com/yash-shah2555/petstore-automation.git'
            }
        }

        stage('Build & Test') {
            steps {
                // Run Maven clean & test
                sh 'mvn clean test'
            }
        }

        stage('Publish TestNG Report') {
            steps {
                // Publish TestNG reports (make sure surefire plugin is generating them)
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/surefire-reports',
                    reportFiles: 'index.html',
                    reportName: 'TestNG Report'
                ])
            }
        }
    }

    post {
        always {
            // Archive test reports (XML) in Jenkins
            junit 'target/surefire-reports/*.xml'
        }
        failure {
            echo '❌ Build Failed!'
        }
        success {
            echo '✅ Build Passed!'
        }
    }
}

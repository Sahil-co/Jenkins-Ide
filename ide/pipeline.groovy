pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', url: 'https://github.com/Sahil-co/Jenkins-Demo.git'
            }
        }
            
        stage('Build') {
            steps {
                // Run Maven on a window agent, shell script
                bat 'mvn clean package'
            }
        

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                // always store the archives even if tests fails
                always { 
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
    }
        
}


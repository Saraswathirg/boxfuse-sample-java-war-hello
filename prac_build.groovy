pipeline{
    agent any
    stages{
        stage("cloning the code"){
            steps{
                println"here the code is cloned"
                sh "ls -l"
                checkout([
                    class:[[name:'GitSCM']],
                    branches:[[name:'*/master']],
                    userRemoteConfigs:[[url:'https://github.com/Saraswathirg/boxfuse-sample-java-war-hello.git']]
                ]
                )
            }
        }
        stage("here the code in converted"){
            steps{
                println"the clone is converted"
                sh "ls -l"
                sh "mvn clean package"
                sh "ls -l"
                sh "ls -lart ./*"
            }
        }
        stage("moved to s3 bucket"){
            steps{
                println"here moved to s3"
            }
        }
    }
}
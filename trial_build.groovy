pipeline{
    agent any
    stages{
        stage("clone the code"){
            steps{
                println "here the clone is coded"
                sh "ls -l"
                checkout([
                    $class:'GitSCM',
                    branches:[[name:'*/master']],
                    userRemoteConfigs:[[url'https://github.com/Saraswathirg/boxfuse-sample-java-war-hello.git']]
                ])
            }
        }
        stage("convert the code"){
            steps{
                println"the code is converted"
            }
        }
        stage("store in to s3"){
            steps{
                println"here the artifacts are stored in the s3"
            }
        }
    }
}
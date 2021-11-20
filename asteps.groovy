pipeline{
    agent any
    parameters{
        string(name:'BRANCH',defaultValue:'master')
    }
    stages{
        stage("build the code"){
            steps{
                println "here the code is built"
                sh "ls -l"
                checkout([
                    $class:'GitSCM',
                    branches:[[name:'${BRANCH}']],
                    userRemoteConfigs:[[url:'https://github.com/Saraswathirg/boxfuse-sample-java-war-hello.git']]
                ])
            }
        }
        stage("code is converted"){
            steps{
                println"here the code is converted"
                sh "ls -lart ./*"
                sh "mvn clean package"
            }
        }
        stage("the artifact is stored"){
            steps{
                println"stored to s3"
                }
            }
            stage("the code is downloaded"){
                steps{
                    println"downloaded from s3"
                }
            }
            stage("the code is copied"){
                steps{
                    println"code copied"
                }
            }
        }
    }

pipeline{
    agent any
    parameters{
        string(name:'BRANCH',defaultValue:'master')
    }
    stages{
        stage("clone the code"){
            steps{
                println"the code is cloned"
                sh "ls -l"
                checkout([
                    $class:'GitSCM',
                    branches:[[name:'${BRANCH}']],
                    userRemoteConfigs:[[url:'https://github.com/Saraswathirg/boxfuse-sample-java-war-hello.git']]
                ])
            }
        }
        stage("build the code"){
            steps{
                println"the code is built"
            }
        }
        stage("stored to s3"){
            steps{
                println"the artifact is stored in s3"
            }
        }
        stage("copying to present location"){
            steps{
                println "the artifactis copied"
            }
        }
        stage(" the artifact is downloaded"){
            steps{
                println"downloaded the artifact"
            }
        }
    }
}
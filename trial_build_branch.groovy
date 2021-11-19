pipeline{
    agent any
    stages{
        stage("build the code"){
            steps{
                println"here the code is built"
                sh "ls -l"
                checkout([
                    $class:'GitSCM',
                    branches:[[name:'*/master']],
                    userRemoteConfigs:[[url:'https://github.com/Saraswathirg/boxfuse-sample-java-war-hello.git']]
                ])
                sh "ls -lart ./*"
            }
        }
        stage("code converted"){
            steps{
                println"the code is converted"
                sh"mvn clean package"
                sh "ls -l target/"
            }
        }
        stage("stored to s3"){
            steps{
                println"the artifact is stored"
                sh "echo $BUILD_NUMBER"
                sh "aws s3 cp target/hello-${BUILD_NUMBER}.war s3://buildsteps/"
            }
        }
    }
}
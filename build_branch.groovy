pipeline{
    agent any
    stages{
        stage("cloning the code"){
            steps{
                println"here the clone is coded"
                sh "ls -l"
                checkout([
                    $class:'GitSCM',
                    branches:[['name:*/master']],
                    userRemoteConfigs:[[url:'https://github.com/Saraswathirg/boxfuse-sample-java-war-hello.git']]
                ]
                )
                sh "ls -lart ./*"
            }
        }
        stage("building the code"){
            steps{
                println"here the code is built"
                sh "mvn clean package"
                sh "ls -l target/"
            }
        }
        stage("uploading to artifacts"){
            steps{
                println "here the artifact is moved to s3"
                sh "aws s3 cp target/hello-${BUILD_NUMBER}.war s3://publicbucke/"
            }
        }
    }
}
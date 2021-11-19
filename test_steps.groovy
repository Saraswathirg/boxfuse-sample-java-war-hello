pipeline{
    agent any
    parameters{
        string(name:'BRANCH_NAME',defaultValue:'master')
        string(name:'BRANCH',defaultValue:'')
        string(name:'BUILD_NUMBER',defaultValue:'')
        string(name:'SERVER_IP',defaultValue:'')
    }
    stages{
        stage("build the code"){
            steps{
            println"here the code is build"
            sh "ls -l"
            checkout([
                $class:'GitSCM',
                branches:[[name:'${BRANCH_NAME}']],
                userRemoteConfigs:[[url:'https://github.com/Saraswathirg/boxfuse-sample-java-war-hello.git']]
            ])
        }
        }
        stage("the code is ectracted"){
            steps{
                println"the code is converted"
                sh "ls -lart ./*"
                sh "mvn clean package"
            }
        }
        stage("stored to s3"){
            steps{
                println"here the artifact is stored to s3"
                sh "aws s3 cp target/hello-${BUILD_NUMBER}.war s3://alltime/${BRANCH_NAME}/${BUILD_NUMBER}/"
            }
        }
        stage("download the artifact"){
            steps{
                println"here the artifcat is downloaded"
                sh"""
                aws s3 ls
                aws s3 ls s3://alltime
                aws s3 ls s3://alltime/${BRANCH}/${BUILD_NUMBER}/
                aws s3 cp s3://alltime/${BRANCH}/${BUILD_NUMBER}/hello-${BUILD_NUMBER}.war ."""
            }
        }
        stage("copy the artifact"){
            steps{
                println"the artifact is copied"
                sh "scp -o StrictHostKeyChecking=no -i /tmp/awsaws.pem hello-${BUILD_NUMBER}.war ec2-user@${SERVER_IP}:/var/lib/tomcat/webapps"
            }
        }
    }
}
pipeline{
    agent any
    parameters{
        string(name:'BRANCH',defaultValue:'master')
        string(name:'BRANCH_NAME',defaultValue:'')
        string(name:'BUILD_NUMBER',defaultValue:'')
        string(name:'SERVER_IP',defaultValue:'')
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
                sh "ls -lart ./*"
                sh "mvn clean package"
            }
        }
        stage("stored to s3"){
            steps{
                println"the artifact is stored in s3"
                sh "aws s3 cp target/hello-${BUILD_NUMBER}.war s3://alltime/${BRANCH}/${BUILD_NUMBER}/"
            }
        }
        stage("copying to present location"){
            steps{
                println "the artifactis copied"
                sh"""
                aws s3 ls
                aws s3 ls s3://alltime
                aws s3 ls s3://alltime/${BRANCH_NAME}/${BUILD_NUMBER}/
                aws s3 cp s3://alltime/${BRANCH_NAME}/${BUILD_NUMBER}/hello-${BUILD_NUMBER}.war ."""
            }
        }
        stage(" the artifact is downloaded"){
            steps{
                println"downloaded the artifact"
                sh "scp -o StrictHostKeychecking=no -i /tmp/awsaws.pem hello-${BUILD_NUMBER}.war ec2-user@${SERVER_IP}:/var/lib/tomcat/webapps"
            }
        }
    }
}


pipeline{
    agent any
    parameters{
        string(name:'${BRANCH}',defaultValue:'')
        string(name:'${BUILD_NUMBER}',defaultValue:'')
        string(name:'${SERVER_IP}',defaultValue:'')
    }
    stages{
        stage("clone the code"){
            steps{
                println"code cloned"
                sh "ls -l"
                checkout([
                    $class:'GitSCM',
                    branches:[[name:'${BRANCH}']],
                    userRemoteConfig:[[url:'https://github.com/Saraswathirg/boxfuse-sample-java-war-hello.git']]
                ])
            }
        }
        stage("build the code"){
            steps{
                println "the code is built"
                sh "ls -lart ./*"
                sh "mvn clean package"
            }
        }
        stage("store to s3"){
            steps{
                println "artifact stored"
                sh "aws s3 cp target/hello-${BUILD_NUMBER}.war s3://alltime/${BRANCH}/${BUILD_NUMBER}/"
            }
        }
        stage("doenload to present location"){
            steps{
                println"hello file downloaded"
                sh "aws cp s3://alltime/${BRANCH}/${BUILD_NUMBER}/hello-${BUILD_NUMBER}.war ."
            }
        }
        stage("copy the file"){
            steps{
                println"file copied"
                sh "aws s3 scp -o strictHostKeychecking=no -i /tmp/awsaws.pem target/hello-${BUILD_NUMBER} ec2-user@${SERVER_IP}:/var/lib/tomcat/webapps"
            }
        }
    }
}
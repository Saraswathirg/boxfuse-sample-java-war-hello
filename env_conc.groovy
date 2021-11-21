pipeline{
    agent any
    environment{
        BRANCH_NAME ="master"
    }
    stages{
        stage("checkout code"){
            steps{
                println "clone the code"
                println "Branch Name ${BRANCH_NAME}"
                println "Branch Name ${env.BRANCH_NAME}"
                sh "ls -l"
                checkout([
                    $class:'GitSCM',
                    branches:[[name:'${env.BRANCH_NAME}']],
                    userRemoteConfigs:[[url:'https://github.com/Saraswathirg/boxfuse-sample-java-war-hello.git']]
                ])
            }
        }
        stage("build the code"){
            steps{
                println"code built"
                sh "mvn clean package"
            sh "ls -lart ./*"
            }
        }
        stage("upload to s3"){
            steps{
                println"uploaded"
                sh "aws s3 cp target/hello-${BUILD_NUMBER}.war s3://alltime/${BRANCH_NAME}/${BUILD_NUMBER}/"
            }
        }
    }
}
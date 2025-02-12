pipeline{
    agent any
    environment{
        BRANCH ="somu"
    }
    stages{
        stage("checkout code"){
            steps{
                println "clone the code"
                println "Branch Name ${BRANCH}"
                println "Branch Name ${env.BRANCH}"
                sh "ls -l"
                /*checkout([
                    $class:'GitSCM',
                    branches:[[name:'${env.BRANCH_NAME}']],
                    userRemoteConfigs:[[url:'https://github.com/Saraswathirg/boxfuse-sample-java-war-hello.git']]
                ])*/
                git branch:"${env.BRANCH}",
                url:'https://github.com/Saraswathirg/boxfuse-sample-java-war-hello.git'
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
                sh "aws s3 cp target/hello-${BUILD_NUMBER}.war s3://alltime/${BRANCH}/${BUILD_NUMBER}/"
            }
        }
    }
}
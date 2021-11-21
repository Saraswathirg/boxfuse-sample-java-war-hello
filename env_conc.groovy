pipeline{
    agent any
    environment{
        BRANCH='${env.BRANCH}'
    }
    stages{
        stage("checkout code"){
            steps{
                println"clone the code"
                sh "ls -l"
                checkout([
                    $class:'GitSCM',
                    branches:[[name:'${env.BRANCH}']],
                    userRemoteConfigs:[[url:'']]
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
                sh "aws s3 cp target/hello-${BUILD_NUMBER}.war s3://alltime/${env.BRANCH}/${BUILD_NUMBER}/"
            }
        }
    }
}
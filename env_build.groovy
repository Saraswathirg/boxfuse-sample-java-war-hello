pipeline{
    agent any
    environment{
        BRANCH="${env.BRANCH_NAME}"
    }
    stages{
        stage("clone the code"){
steps{
    println"here the clone is coded"
    sh "ls -l"
    sh "ls -lart ./*"
    git branch:"${BRANCH_NAME}"
    url:"https://github.com/Saraswathirg/boxfuse-sample-java-war-hello.git"
         }
        }
        stage("build the code"){
            steps{
                println "here the code is built"
                sh "mvn clean package"
                sh "ls -l target/"
            }
        }
        stage("stored to s3"){
            steps{
                println"the artifact is stored to s3"
                sh "echo $BUILD_NUMBER"
                sh "aws s3 cp target/hello-${BUILD_NUMBER}.war s3://alltime/${BRANCH}/${BUILD_NUMBER}/"
            }
        }
    }
}
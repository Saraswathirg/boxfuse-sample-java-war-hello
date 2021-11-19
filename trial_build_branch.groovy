pipeline{
    agent any
    stages{
        stage("build the code"){
            steps{
                println"here the code is built"
            }
        }
        stage("code converted"){
            steps{
                println"the code is converted"
            }
        }
        stage("stored to s3"){
            steps{
                println"the artifact is stored"
            }
        }
    }
}
pipeline{
    agent any
    stages{
        stage("build the code"){
            steps{
            println"here the code is build"
        }
        }
        stage("the code is ectracted"){
            steps{
                println"the code is converted"
            }
        }
        stage("stored to s3"){
            steps{
                println"here the artifact is stored to s3"
            }
        }
        stage("download the artifact"){
            steps{
                println"here the artifcat is downloaded"
            }
        }
        stage("copy the artifact"){
            steps{
                println"the artifact is copied"
            }
        }
    }
}
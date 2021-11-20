pipeline{
    agent any
    stages{
        stage("clone the code"){
            steps{
                println"the code is cloned"
            }
        }
        stage("build the code"){
            steps{
                println"the code is built"
            }
        }
        stage("stored to s3"){
            steps{
                println"the artifact is stored in s3"
            }
        }
        stage("copying to present location"){
            steps{
                println "the artifactis copied"
            }
        }
        stage(" the artifact is downloaded"){
            steps{
                println"downloaded the artifact"
            }
        }
    }
}
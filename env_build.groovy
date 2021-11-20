pipeline{
    agent any
    stages{
        stage("clone the code"){
steps{
    println"here the clone is coded"
         }
        }
        stage("build the code"){
            steps{
                println "here the code is built"
            }
        }
        stage("stored to s3"){
            steps{
                println"the artifact is stored to s3"
            }
        }
    }
}
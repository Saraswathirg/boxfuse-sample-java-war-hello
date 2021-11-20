pipeline{
    agent any
    stages{
        stage("build the code"){
            steps{
                println "here the code is built"
            }
        }
        stage("code is converted"){
            steps{
                println"here the code is converted"
            }
        }
        stage("the artifact is stored"){
            steps{
                println"stored to s3"
                }
            }
            stage("the code is downloaded"){
                steps{
                    println"downloaded from s3"
                }
            }
            stage("the code is copied"){
                steps{
                    println"code copied"
                }
            }
        }
    }

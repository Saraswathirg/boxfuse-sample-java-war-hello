//Declarative pipeline
pipeline{
    agent any
    stages{
        stage("coding"){
            steps{
                println"clone our code to our repo"
            }
        }
    stage("vuild code"){
        steps{
            println"mvn clean package"
        }
    }
    stage("upload artifacts to s3") {
        steps{
            println"upload artifacts to s3"
            }
        }
    }
}

pipeline{
    agent any
    stages{
        stage("download the artifacts"){
            steps{
                println"here the artifact downloaded"
            }
        }
        stage("here the code is copied"){
            steps{
                println"code copied"
            }
        }
    }
}
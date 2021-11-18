pipeline{
    agent any
    stages{
        stage("download artifacts"){
            steps{
                println"here the artifact is downloaded"
            }
        }
stage(copy artifacts){
steps{
    println"here the artifact is copied"
}
}
    }
}
pipeline{
    agent any
    stages{
        stage("adding the code"){
            steps{
                println"clone added"
            }
        }
    stage("building code"){
        steps{
            println"code builded"
        }
    }
} 
}